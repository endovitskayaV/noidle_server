package ru.vsu.noidle_server.service;

import ru.vsu.noidle_server.exception.ServiceException;
import ru.vsu.noidle_server.model.domain.TeamEntity;
import ru.vsu.noidle_server.model.dto.NewTeamDto;
import ru.vsu.noidle_server.model.dto.TeamDto;
import ru.vsu.noidle_server.model.dto.TeamDtoShort;

import java.util.List;
import java.util.UUID;

public interface TeamService {
    TeamDto edit(TeamDto teamDto) throws ServiceException;

    TeamDto add(NewTeamDto newTeamDto) throws ServiceException;

    TeamDto getById(UUID id) throws ServiceException;

    TeamEntity getEntityById(UUID id) throws ServiceException;

    TeamDtoShort getShortByName(String name) throws ServiceException;

    TeamDtoShort getShortById(UUID id) throws ServiceException;

    void delete(UUID id) throws ServiceException;

    List<TeamDto> getAll() throws ServiceException;

    void addTeamMember(UUID userId, UUID teamId) throws ServiceException;

    boolean checkIfAddedTeamMember(UUID userId, UUID teamId);

    void removeTeamMember(UUID userId, UUID teamId) throws ServiceException;
}
