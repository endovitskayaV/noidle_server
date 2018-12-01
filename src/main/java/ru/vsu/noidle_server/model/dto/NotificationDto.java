package ru.vsu.noidle_server.model.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"achievement", "user"})
@ToString
public class NotificationDto {

    private AchievementDto achievement;

    //when sending info about colleagues
    private  UserDtoForNotification user;

    private List<RequirementDto> requirements;
}
