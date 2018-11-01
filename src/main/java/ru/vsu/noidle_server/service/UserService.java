package ru.vsu.noidle_server.service;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import ru.vsu.noidle_server.model.dto.UserDto;

import java.util.UUID;

public interface UserService {

    UserDto getById(UUID uuid);

    UserDto saveUser(OAuth2Authentication user);

    UserDto getUser(OAuth2Authentication user);

}
