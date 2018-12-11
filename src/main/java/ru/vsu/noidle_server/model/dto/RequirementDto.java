package ru.vsu.noidle_server.model.dto;

import lombok.*;
import ru.vsu.noidle_server.model.StatisticsSubType;
import ru.vsu.noidle_server.model.StatisticsType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"statisticsType", "statisticsSubType", "name", "extraValue"})
@ToString
public class RequirementDto {
    private StatisticsType statisticsType;

    private StatisticsSubType statisticsSubType;

    private String name;

    private Long extraValue;

    private Float teamContributionRate;
}
