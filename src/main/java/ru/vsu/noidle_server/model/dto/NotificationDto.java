package ru.vsu.noidle_server.model.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"level", "userId"})
@ToString
public class NotificationDto {

    private LevelDto level;

    //when sending info about colleagues
    private UUID userId;

    private List<AchievementForNotification> achievements;
}
