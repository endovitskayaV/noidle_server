package ru.vsu.noidle_server.service;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import ru.vsu.noidle_server.model.dto.UserDto;

public interface UserService {

    UserDto saveUser(OAuth2Authentication user);
}
