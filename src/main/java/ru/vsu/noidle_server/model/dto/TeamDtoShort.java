package ru.vsu.noidle_server.model.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class TeamDtoShort {
    private UUID id;

    private String name;
}
