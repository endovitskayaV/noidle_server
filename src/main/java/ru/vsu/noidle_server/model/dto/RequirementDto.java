package ru.vsu.noidle_server.model.dto;

import lombok.*;
import ru.vsu.noidle_server.model.StatisticsSubType;
import ru.vsu.noidle_server.model.StatisticsType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class RequirementDto {
    private StatisticsType statisticsType;

    private StatisticsSubType statisticsSubType;

    private String extraValue;

    private Long value;

    private Float teamContributionRate;
}
