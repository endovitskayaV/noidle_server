package ru.vsu.noidle_server.service;

import ru.vsu.noidle_server.exception.ServiceException;
import ru.vsu.noidle_server.model.domain.TeamEntity;
import ru.vsu.noidle_server.model.dto.TeamDto;
import ru.vsu.noidle_server.model.dto.TeamDtoShort;

import java.util.UUID;

public interface TeamService {
    TeamDto save(TeamDto teamDto);

    TeamDto getById(UUID id);

    TeamEntity getEntityById(UUID id) throws ServiceException;

    TeamDtoShort getByIdOrName(String idOrName);

    TeamEntity getEntityByIdOrName(String idOrName);
}
