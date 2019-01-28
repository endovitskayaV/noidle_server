package ru.vsu.noidle_server.model.domain;

import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class UserTeamId implements Serializable {
    private UUID team;
    private UUID user;
}
