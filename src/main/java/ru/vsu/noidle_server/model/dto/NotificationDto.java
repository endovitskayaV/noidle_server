package ru.vsu.noidle_server.model.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"level", "user"})
@ToString
public class NotificationDto {

    private LevelDto level;

    //when sending info about colleagues
    private  UserDtoForNotification user;

    private List<AchievementForNotification> achievements;
}
