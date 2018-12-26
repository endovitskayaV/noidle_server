package ru.vsu.noidle_server.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "teamDto")
public class NewTeamDto {
    TeamDto teamDto;

    //number of teams
    Integer overall;
}
