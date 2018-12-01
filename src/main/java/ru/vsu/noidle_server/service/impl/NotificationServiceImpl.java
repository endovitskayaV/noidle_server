package ru.vsu.noidle_server.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vsu.noidle_server.exception.ServiceException;
import ru.vsu.noidle_server.model.domain.NotificationEntity;
import ru.vsu.noidle_server.model.domain.RequirementEntity;
import ru.vsu.noidle_server.model.domain.UserEntity;
import ru.vsu.noidle_server.model.dto.NotificationDto;
import ru.vsu.noidle_server.model.mapper.DataMapper;
import ru.vsu.noidle_server.model.repository.*;
import ru.vsu.noidle_server.service.NotificationService;
import ru.vsu.noidle_server.service.UserService;

import javax.persistence.EntityNotFoundException;
import java.time.OffsetDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    private final RequirementRepository requirementRepository;
    private final AchievementRepository achievementRepository;
    private final UserService userService;
    private final DataMapper dataMapper;

    @Transactional
    @Override
    public void setNotifications(UUID userId) throws ServiceException {
        Set<NotificationEntity> notifications = new HashSet<>();
        UserEntity user = userService.getEntityById(userId);

        //level
        Long newLevel = user.getLevel().getLevelNumber() + 1;
        addIfNotNull(doSetNotification(user, newLevel), notifications);


        //not level
        achievementRepository.getAllByLevelNumberNull().forEach(achievement ->
                addIfNotNull(doSetNotification(user, achievement.getId()), notifications)
        );

        //colleagues
        user.getColleagues().forEach(colleague -> {
            colleague.setNotifications(notifications);
        });

        userService.save(user);
        log.info("Set user notifications {}", user.getNotifications());
    }

    @Override
    public List<NotificationDto> getAll(UUID userId) throws ServiceException {
        return formNotifications(userService.getEntityById(userId));
    }

    private List<NotificationDto> formNotifications(UserEntity user) {
        if (user.getNotifications().isEmpty()) {
            return Collections.emptyList();
        }

        List<NotificationDto> notifications = new ArrayList<>();

        user.getNotifications().forEach(notification ->
                notifications.add(
                        new NotificationDto(
                                dataMapper.toDto(notification.getAchievement()),
                                dataMapper.toDtoForNotification(user),
                                dataMapper.toDto(requirementRepository.getAllByAchievementId(notification.getAchievement().getId()))
                        )
                )
        );
        return notifications;
    }

    @Nullable
    private NotificationEntity doSetNotification(UserEntity user, Long achievementId) {
        List<RequirementEntity> requirements =
                requirementRepository.getAllByAchievementId(achievementId);

        boolean levelAchieved = !requirements.isEmpty() &&
                requirements.stream().allMatch(requirement ->
                        requirement.anyFits(user.getStatistics()));

        NotificationEntity notification = null;
        if (levelAchieved) {
            notification = new NotificationEntity(
                    user,
                    achievementRepository.getByLevelNumber(user.getLevel().getLevelNumber() + 1),
                    OffsetDateTime.now()
            );
            user.addNotification(notification);
        }
        return notification;
    }

    private <T> void addIfNotNull(T elem, Collection<T> collection) {
        if (elem != null) collection.add(elem);
    }
}
