package ru.vsu.noidle_server.service;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import ru.vsu.noidle_server.exception.ServiceException;
import ru.vsu.noidle_server.model.domain.UserEntity;
import ru.vsu.noidle_server.model.dto.UserDto;

import java.util.UUID;

public interface UserService {

    UserEntity getEntityById(UUID id) throws ServiceException;

    void save(UserEntity userEntity);

    UserDto getById(UUID id);

    UserDto save(OAuth2Authentication user);

    UserDto getDto(OAuth2Authentication user);

}
