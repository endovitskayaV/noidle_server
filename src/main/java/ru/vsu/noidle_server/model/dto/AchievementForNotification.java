package ru.vsu.noidle_server.model.dto;

import lombok.*;
import ru.vsu.noidle_server.model.SubType;
import ru.vsu.noidle_server.model.Type;
import ru.vsu.noidle_server.model.domain.RequirementEntity;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"type", "subType", "name", "value"})
public class AchievementForNotification {
    private Type type;

    private SubType subType;

    private String name;

    private Long value;

    public static List<AchievementForNotification> fromRequirements(List<RequirementEntity> requirements) {
        List<AchievementForNotification> achievements = new ArrayList<>();
        requirements.forEach(requirement ->
                achievements.add(
                        new AchievementForNotification(
                                requirement.getType(),
                                requirement.getSubType(),
                                requirement.getName(),
                                requirement.getValue()
                        )
                )
        );
        return achievements;
    }
}