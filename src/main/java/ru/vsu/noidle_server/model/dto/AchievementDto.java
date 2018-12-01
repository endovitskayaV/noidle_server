package ru.vsu.noidle_server.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "levelNumber")
@ToString
public class AchievementDto {

    private Long levelNumber;

    private String name;
}
