package ru.vsu.noidle_server.model.dto;

import lombok.*;
import ru.vsu.noidle_server.model.AchievementType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "levelNumber")
@ToString
public class AchievementDto {

    private Long levelNumber;

    private String name;

    private AchievementType type;
}
