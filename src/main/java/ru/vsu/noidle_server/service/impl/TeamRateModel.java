package ru.vsu.noidle_server.service.impl;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"requirementId", "statisticValue", "rate"})
class TeamRateModel {
    private Long requirementId;
    private Long statisticValue;
    private Float rate;

    TeamRateModel(Long requirementId, Long statisticValue) {
        this.requirementId = requirementId;
        this.statisticValue = statisticValue;
        rate = (float) 0;
    }
}
