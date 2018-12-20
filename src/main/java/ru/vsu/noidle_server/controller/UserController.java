package ru.vsu.noidle_server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.vsu.noidle_server.exception.ServiceException;
import ru.vsu.noidle_server.model.dto.UserDto;
import ru.vsu.noidle_server.service.UserService;

import java.util.UUID;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

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

    @PostMapping("/users/{userId}/teams/add/{teamId}")
    @ResponseBody
    public ResponseEntity addTeam(@PathVariable UUID userId, @PathVariable UUID teamId) {
        try {
            userService.addTeam(userId, teamId);
        } catch (ServiceException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/profile")
    public String getProfile(ModelMap modelMap) {
        UserDto user;
        try {
            user = userService.getByAuth(AuthUtils.getUser());
            modelMap.addAttribute("user", user);
            return "profile";
        } catch (ServiceException e) {
            modelMap.addAttribute("error", "User not found");
            return "error";
        }
    }
}
