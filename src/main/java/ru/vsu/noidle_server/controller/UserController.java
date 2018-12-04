package ru.vsu.noidle_server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vsu.noidle_server.model.domain.TeamEntity;
import ru.vsu.noidle_server.model.dto.UserDto;
import ru.vsu.noidle_server.service.UserService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user")
    public UserDto user(OAuth2Authentication user) {
        return userService.getDto(user);
    }

    @GetMapping("/users/{id}")
    public UserDto user(@PathVariable UUID id) {
        return userService.getById(id);
    }

    @PostMapping("/users/{userId}/teams/add/{teamNameOrId}")
    public ResponseEntity addTeam(@PathVariable UUID userId, @PathVariable String teamNameOrId) {
        TeamEntity teamEntity = userService.addTeam(userId, teamNameOrId);
        return teamEntity == null ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok(teamEntity.getName());
    }
}
