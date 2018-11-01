package ru.vsu.noidle_server.model.dto;

import lombok.*;
import ru.vsu.noidle_server.model.Achievement;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class AchievementDto {

    private UUID id;

    private Achievement type;

    private String name;

    private Long value;

    private UUID userId;
}
