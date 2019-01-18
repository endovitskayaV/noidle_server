package ru.vsu.noidle_server.model.dto;

import lombok.*;
import org.apache.commons.lang3.tuple.Triple;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class AchievementDashboardDto {

    private Long level;

    private String name;

    //name-value-team_contribution_rate
    private List<Triple<String, String, Float>> requirements;

    /**
     * list contains elements (comments) if this was achieved
     */
    private List<String> achievedComments;

}
