package ru.vsu.noidle_server.model.dto;

import lombok.*;
import ru.vsu.noidle_server.model.SubType;
import ru.vsu.noidle_server.model.Type;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class NotificationDto {

    private UUID id;

    private Type type;

    private SubType subType;

    private Integer level;

    private Long value;

    //when sending info about colleagues
    private UUID userId;
}
