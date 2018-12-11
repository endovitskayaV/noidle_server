package ru.vsu.noidle_server.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vsu.noidle_server.exception.ServiceException;
import ru.vsu.noidle_server.model.domain.AchievementEntity;
import ru.vsu.noidle_server.model.domain.NotificationEntity;
import ru.vsu.noidle_server.model.domain.RequirementEntity;
import ru.vsu.noidle_server.model.domain.UserEntity;
import ru.vsu.noidle_server.model.dto.NotificationDto;
import ru.vsu.noidle_server.model.mapper.DataMapper;
import ru.vsu.noidle_server.model.repository.AchievementRepository;
import ru.vsu.noidle_server.model.repository.NotificationRepository;
import ru.vsu.noidle_server.model.repository.RequirementRepository;
import ru.vsu.noidle_server.service.NotificationService;
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
    private final UserService userService;
    private final DataMapper dataMapper;
    private static final int MIN_LEVEL = 1;

    @Transactional
    @Override
    public void setNotifications(UUID userId) throws ServiceException {
        Set<NotificationEntity> notifications = new HashSet<>();
        UserEntity user = userService.getEntityById(userId);

        //level
        addIfNotNull(doSetOwnNotification(user, getNextLevelNumber(user)), notifications);

        //not level
        final Set<AchievementEntity> extraAchievements = user.getExtraAchievements();
        achievementRepository.getAllByLevelNumberNull()
                .stream()
                .filter(achievementEntity -> !extraAchievements.contains(achievementEntity))
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

    @Nullable
    private NotificationEntity doSetOwnNotification(UserEntity user, Long achievementId) {
        List<RequirementEntity> requirements =
                requirementRepository.getAllByAchievementId(achievementId);

        boolean levelAchieved = !requirements.isEmpty() &&
                requirements.stream().allMatch(requirement ->
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
