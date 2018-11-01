package ru.vsu.noidle_server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vsu.noidle_server.model.dto.UserDto;
import ru.vsu.noidle_server.service.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user")
    public UserDto user(OAuth2Authentication user) {
        return userService.getUser(user);
    }

    @GetMapping("/users/{}")
    public UserDto user(OAuth2Authentication user) {
        return userService.getUser(user);
    }
}
