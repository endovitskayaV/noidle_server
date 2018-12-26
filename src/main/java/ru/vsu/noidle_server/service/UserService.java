package ru.vsu.noidle_server.service;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import ru.vsu.noidle_server.exception.ServiceException;
import ru.vsu.noidle_server.model.domain.UserEntity;
import ru.vsu.noidle_server.model.dto.TeamDto;
import ru.vsu.noidle_server.model.dto.UserDto;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserEntity getEntityById(UUID id) throws ServiceException;

    void save(UserEntity userEntity);

    UserDto getById(UUID id) throws ServiceException;

    UserDto getByName(String name) throws ServiceException;

    UserDto getByAuth() throws ServiceException;

    UserDto save(OAuth2Authentication user);

    UserDto getDto(OAuth2Authentication user);

    void addTeamMember(UUID userId, UUID teamId) throws ServiceException;

    void removeTeamMember(UUID userId, UUID teamId) throws ServiceException;

    UserEntity getEntityByAuth() throws ServiceException;

    List<TeamDto> getTeams() throws ServiceException;
}
