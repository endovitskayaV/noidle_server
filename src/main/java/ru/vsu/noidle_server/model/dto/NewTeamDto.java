package ru.vsu.noidle_server.model.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class NewTeamDto {
    private String name;

    private UUID userId;
}
