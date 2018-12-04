package ru.vsu.noidle_server.service;

import ru.vsu.noidle_server.model.domain.TeamEntity;
import ru.vsu.noidle_server.model.dto.TeamDto;

import java.util.UUID;

public interface TeamService {
    TeamDto save (TeamDto teamDto);

    TeamDto getById(UUID id);

    TeamDto getByIdOrName(String idOrName);

    TeamEntity getEntityByIdOrName(String idOrName);
}
