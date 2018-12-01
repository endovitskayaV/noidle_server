package ru.vsu.noidle_server.model.dto;

import lombok.*;
import ru.vsu.noidle_server.model.SubType;
import ru.vsu.noidle_server.model.Type;

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

    private Type type;

    private SubType subType;

    private String name;

    private Long value;

    private OffsetDateTime date;
}
