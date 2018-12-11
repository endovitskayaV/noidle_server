package ru.vsu.noidle_server.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vsu.noidle_server.exception.ServiceException;
import ru.vsu.noidle_server.model.AchievementType;
import ru.vsu.noidle_server.model.domain.*;
import ru.vsu.noidle_server.model.dto.NotificationDto;
import ru.vsu.noidle_server.model.mapper.DataMapper;
import ru.vsu.noidle_server.model.repository.AchievementRepository;
import ru.vsu.noidle_server.model.repository.NotificationRepository;
import ru.vsu.noidle_server.model.repository.RequirementRepository;
import ru.vsu.noidle_server.model.repository.StatisticsRepository;
import ru.vsu.noidle_server.service.NotificationService;
import ru.vsu.noidle_server.service.TeamService;
import ru.vsu.noidle_server.service.UserService;

import java.time.OffsetDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    private final RequirementRepository requirementRepository;
    private final AchievementRepository achievementRepository;
    private final NotificationRepository notificationRepository;
    private final StatisticsRepository statisticsRepository;
    private final TeamService teamService;
    private final UserService userService;
    private final DataMapper dataMapper;
    private static final int MIN_LEVEL = 1;

    @Transactional
    @Override
    public void setNotifications(UUID userId, UUID teamId) throws ServiceException {
        Set<NotificationEntity> notifications = new HashSet<>();
        UserEntity user = userService.getEntityById(userId);

        //level
        addIfNotNull(doSetOwnNotification(user, getNextLevelNumber(user)), notifications);

        //not level
        final Set<AchievementEntity> nonLevelAchievements = user.getNonLevelAchievements();
        achievementRepository.getAllByType(AchievementType.EXTRA)
                .stream()
                .filter(achievementEntity -> !nonLevelAchievements.contains(achievementEntity))
                .forEach(achievement ->
                        addIfNotNull(doSetOwnNotification(user, achievement.getId()), notifications)
                );
        notifications.forEach(notificationRepository::save);
        log.info("Set user {} notifications {}", user, user.getAllNotifications());

        //colleagues
        Set<NotificationEntity> colleaguesNotifications = notifications.stream()
                .map(NotificationEntity::new)
                .collect(Collectors.toSet());

        user.getColleagues().forEach(colleague -> {
            colleaguesNotifications.forEach(notification -> {
                notification.setToWhomUser(colleague);
                colleague.addNotification(notification);
            });
            colleaguesNotifications.forEach(notificationRepository::save);
            log.info("Set colleague {} notifications {}", user, user.getAllNotifications());
        });
        log.info("Set user {} notifications {}", user, notifications);

        //team
        doSetTeamNotifications(teamService.getEntityById(teamId));
    }

    @Transactional
    @Override
    public List<NotificationDto> getAll(UUID userId) throws ServiceException {
        return formNotifications(userService.getEntityById(userId));
    }

    private List<NotificationDto> formNotifications(UserEntity user) {
        List<NotificationEntity> notificationEntities = new ArrayList<>(user.getAllNotifications());

        if (notificationEntities.isEmpty()) {
            return Collections.emptyList();
        }

        List<NotificationDto> notifications = new ArrayList<>();

        notificationEntities.stream()
                .filter(notification -> !notification.isSent())
                .forEach(notification -> {
                    notifications.add(new NotificationDto(
                            dataMapper.toDto(notification.getAchievement()),
                            dataMapper.toDtoForNotification(notification.getAboutUser()),
                            dataMapper.toDto(requirementRepository.getAllByAchievementId(notification.getAchievement().getId())),
                            notification.getDate().toInstant().toEpochMilli()
                    ));
                    notification.setSent(true);
                    notificationRepository.save(notification);
                });

        return notifications;
    }

    private void doSetTeamNotifications(TeamEntity team) {
        achievementRepository.getAllByType(AchievementType.TEAM)
                .forEach(achievement -> {
                    List<RequirementEntity> requirements = requirementRepository.getAllByAchievementId(achievement.getId());
                    Map<UUID, TeamRateModel> rates = new HashMap<>();

                    if (requirements.isEmpty()) {
                        return;
                    }
                    //set values
                    requirements.forEach(requirement -> {
                        team.getUsers().forEach(teamMember -> {
                            StatisticsEntity statistics = statisticsRepository.getByTypeAndSubTypeAndExtraValueAndUserId(
                                    requirement.getStatisticsType(), requirement.getStatisticsSubType(), requirement.getExtraValue(), teamMember.getId());
                            if (statistics != null && statistics.getValue() != null) {
                                rates.put(teamMember.getId(), new TeamRateModel(requirement.getId(), statistics.getValue()));
                            }
                        });
                    });


                    if (rates.isEmpty()) {
                        return;
                    }
                    //set rates
                    long sum = rates.values().stream().mapToLong(TeamRateModel::getStatisticValue).sum();
                    rates.forEach((key, value) -> value.setRate((float) value.getStatisticValue() / sum));

                    //get ones who achieved
                    team.getUsers().stream()
                            .filter(teamMember ->
                                    //achieved: meet requirement and has not achieved already
                                    requirements.stream().allMatch(requirement -> {
                                        TeamRateModel teamRate = rates.get(teamMember.getId());

                                        boolean fit = !teamMember.getNonLevelAchievements().contains(achievement) &&
                                                teamRate != null &&
                                                requirement.getValue().compareTo(teamRate.getStatisticValue()) <= 0 &&
                                                requirement.getTeamContributionRate().compareTo(teamRate.getRate()) <= 0;
                                        return fit;
                                    })
                            ).collect(Collectors.toSet())
                            .forEach(teamMember -> {//form notifications
                                //personal
                                NotificationEntity notification = new NotificationEntity(
                                        teamMember,
                                        achievementRepository.findById(achievement.getId()).orElse(null),
                                        OffsetDateTime.now()
                                );
                                teamMember.addNotification(notification);
                                notificationRepository.save(notification);
                                log.info("Set team {} notification {}", team, notification);

                                //colleagues
                                teamMember.getColleagues().forEach(colleague -> {
                                    NotificationEntity notification1 = new NotificationEntity(
                                            teamMember,
                                            colleague,
                                            achievementRepository.findById(achievement.getId()).orElse(null),
                                            OffsetDateTime.now());

                                    notificationRepository.save(notification1);
                                    colleague.addNotification(notification1);
                                    log.info("Set team {} notification {}", team, notification1);
                                });
                            });
                });
    }

    @Nullable
    private NotificationEntity doSetOwnNotification(UserEntity user, Long achievementId) {
        List<RequirementEntity> requirements =
                requirementRepository.getAllByAchievementId(achievementId);

        boolean levelAchieved = !requirements.isEmpty() &&
                requirements.stream()
                        .allMatch(requirement ->
                                requirement.anyFits(user.getStatistics()));


        NotificationEntity notification = null;
        if (levelAchieved) {
            notification = new NotificationEntity(
                    user,
                    achievementRepository.findById(achievementId).orElse(null),
                    OffsetDateTime.now()
            );
            user.addNotification(notification);
        }
        return notification;
    }

    private <T> void addIfNotNull(T elem, Collection<T> collection) {
        if (elem != null) collection.add(elem);
    }

    private Long getNextLevelNumber(UserEntity user) {
        return user.getLevel() == null ?
                MIN_LEVEL :
                user.getLevel().getLevelNumber() + 1;
    }
}
