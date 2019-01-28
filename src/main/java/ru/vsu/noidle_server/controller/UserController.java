package ru.vsu.noidle_server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.vsu.noidle_server.exception.ServiceException;
import ru.vsu.noidle_server.model.UserRole;
import ru.vsu.noidle_server.model.dto.UserDto;
import ru.vsu.noidle_server.service.TeamService;
import ru.vsu.noidle_server.service.UserService;

import java.util.UUID;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;
    private final TeamService teamService;

    @GetMapping("/user")
    @ResponseBody
    public UserDto user(OAuth2Authentication user) {
        return userService.getDto(user);
    }

    @GetMapping("/users/{id}")
    @ResponseBody
    public ResponseEntity<UserDto> getById(@PathVariable UUID id) {
        UserDto user;
        try {
            user = userService.getById(id);
        } catch (ServiceException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping("/users")
    @ResponseBody
    public ResponseEntity<UserDto> getByName(@RequestParam("name") String name) {
        UserDto user;
        try {
            user = userService.getByName(name);
        } catch (ServiceException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }


    @PostMapping(value = "/users/{userId}/teams/add/{teamId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity addTeamMember(@PathVariable UUID userId, @PathVariable UUID teamId, @RequestBody UserRole role) {
        try {
            teamService.addTeamMember(userId, teamId, role);
        } catch (ServiceException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/users/{userId}/teams/check/add/{teamId}")
    @ResponseBody
    public ResponseEntity checkIfAddedTeamMember(@PathVariable UUID userId, @PathVariable UUID teamId) {
        return teamService.checkIfAddedTeamMember(userId, teamId) ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();
    }

    @PostMapping("/users/{userId}/teams/remove/{teamId}")
    @ResponseBody
    public ResponseEntity removeTeamMember(@PathVariable UUID userId, @PathVariable UUID teamId) {
        try {
            teamService.removeTeamMember(userId, teamId);
        } catch (ServiceException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/profile")
    public String getProfile(ModelMap modelMap) {
        UserDto user;
        try {
            user = userService.getByAuth();
            modelMap.addAttribute("user", user);
            return "profile";
        } catch (ServiceException e) {
            modelMap.addAttribute("error", "User not found");
            return "error";
        }
    }
}
