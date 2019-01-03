package ru.vsu.noidle_server.model.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class StatisticsDashboardEntity {
    private String type;
    private String subType;
    private String extraValue;
    private Long value;
}