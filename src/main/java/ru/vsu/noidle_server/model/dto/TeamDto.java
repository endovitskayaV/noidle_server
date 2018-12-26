package ru.vsu.noidle_server.model.dto;

import lombok.*;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(exclude = "users")
public class TeamDto {

    private UUID id;

    private String name;

    private String photo;

    private OffsetDateTime created;

    private Collection<UserDto> users;
}