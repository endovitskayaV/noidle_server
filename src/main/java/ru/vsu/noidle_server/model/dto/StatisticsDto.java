package ru.vsu.noidle_server.model.dto;

import lombok.*;
import ru.vsu.noidle_server.model.StatisticsSubType;
import ru.vsu.noidle_server.model.StatisticsType;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class StatisticsDto {

    private UUID id;

    private StatisticsType type;

    private StatisticsSubType subType;

    private String extraValue;

    private Long value;

    private OffsetDateTime date;
}
