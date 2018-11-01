package ru.vsu.noidle_server.model.dto;

import lombok.*;

import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "email")
@ToString(exclude = "achievements")
public class UserDto {

    private String email;

    private String name;

    private String photo;

    private Collection<AchievementDto> achievements;
}
