package ru.vsu.noidle_server.service;

import ru.vsu.noidle_server.exception.ServiceException;
import ru.vsu.noidle_server.model.domain.TeamEntity;
import ru.vsu.noidle_server.model.dto.TeamDto;
import ru.vsu.noidle_server.model.dto.TeamDtoShort;

import java.util.List;
import java.util.UUID;

public interface TeamService {
    TeamDto save(TeamDto teamDto) throws ServiceException;

    TeamDto getById(UUID id) throws ServiceException;

    TeamEntity getEntityById(UUID id) throws ServiceException;

    TeamDtoShort getShortByName(String name) throws ServiceException;

    List<TeamDto> getAll();

    void delete(UUID id) throws ServiceException;
}
