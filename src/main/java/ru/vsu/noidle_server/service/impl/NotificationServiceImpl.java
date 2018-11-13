package ru.vsu.noidle_server.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.vsu.noidle_server.model.domain.NotificationEntity;
import ru.vsu.noidle_server.model.domain.UserEntity;
import ru.vsu.noidle_server.model.dto.NotificationDto;
import ru.vsu.noidle_server.model.repository.AchievementRepository;
import ru.vsu.noidle_server.model.repository.NotificationRepository;
import ru.vsu.noidle_server.model.repository.UserRepository;
import ru.vsu.noidle_server.service.NotificationService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final AchievementRepository achievementRepository;
    private final UserRepository userRepository;

    @Override
    public List<NotificationDto> getAll(UUID userId) {
        UserEntity userEntity = userRepository.findById(userId).orElse(null);
        if (userEntity == null) {
            return null;
        }
        List<NotificationDto> notificationsToSend = new ArrayList<>();
        List<NotificationEntity> allNotifications = notificationRepository.findAll();

        achievementRepository.getAllByUserId(userId).forEach(achievement -> {
            NotificationEntity notificationEntity = allNotifications.stream()
                    .filter(notification ->
                            notification.getType().equals(achievement.getType()) &&
                                    notification.getSubType().equals(achievement.getSubType()))
                    .findFirst().orElse(null);
            if (notificationEntity != null) {

                //achievement value >= notification value
                if ((achievement.getValue().compareTo(notificationEntity.getValue()) > -1) &&
                        !userEntity.getNotificationSent().get(notificationEntity)) {
                    notificationsToSend.add(new NotificationDto(
                            UUID.randomUUID(),
                            notificationEntity.getType(),
                            notificationEntity.getSubType(),
                            notificationEntity.getLevel(),
                            notificationEntity.getValue(),
                            userId
                    ));
                    userEntity.getNotificationSent().put(notificationEntity, true);
                }
            }
        });
        return notificationsToSend;
    }
}
