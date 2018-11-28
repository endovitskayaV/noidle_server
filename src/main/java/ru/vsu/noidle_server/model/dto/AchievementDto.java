package ru.vsu.noidle_server.model.dto;

import lombok.*;
import ru.vsu.noidle_server.model.SubType;
import ru.vsu.noidle_server.model.Type;

import java.math.BigInteger;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class AchievementDto {

    private UUID id;

    private Type type;

    private SubType subType;

    private String name;

    private BigInteger value;

    private Long date;

    private UUID userId;
}
