package ru.vsu.noidle_server.service;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import ru.vsu.noidle_server.model.dto.UserDto;

import java.security.Principal;

public interface UserService {

//    UserEntity getById(UUID id);
//
//    List<UserEntity> getAll();

    UserDto saveUser(OAuth2Authentication user);
}
