package ru.vsu.noidle_server.model.dto;

import lombok.*;
import ru.vsu.noidle_server.model.UserRole;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.Map;
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

  //  private Map<UserDto, UserRole> users;

    private Collection<UserDto> users;
}