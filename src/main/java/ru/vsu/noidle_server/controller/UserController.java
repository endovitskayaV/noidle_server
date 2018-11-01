package ru.vsu.noidle_server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vsu.noidle_server.model.dto.UserDto;
import ru.vsu.noidle_server.service.UserService;


@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @RequestMapping({"/user", "/login/gitlab"})
    public UserDto user(OAuth2Authentication user) {
        return userService.saveUser(user);
    }
}
