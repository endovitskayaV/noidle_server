package ru.vsu.noidle_server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.vsu.noidle_server.exception.ServiceException;
import ru.vsu.noidle_server.model.dto.NewTeamDto;
import ru.vsu.noidle_server.model.dto.TeamDto;
import ru.vsu.noidle_server.model.dto.TeamDtoShort;
import ru.vsu.noidle_server.service.TeamService;
import ru.vsu.noidle_server.service.UserService;
import ru.vsu.noidle_server.utils.AuthUtils;

import java.util.Collections;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/teams")
public class TeamController {
    private final TeamService teamService;
    private final UserService userService;

    @GetMapping("/short/{id}")
    @ResponseBody
    public ResponseEntity<TeamDtoShort> getShortByName(@PathVariable("id") UUID id) {
        TeamDtoShort teamDtoShort;
        try {
            teamDtoShort = teamService.getShortById(id);
        } catch (ServiceException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(teamDtoShort);
    }

    @GetMapping("/short")
    @ResponseBody
    public ResponseEntity<TeamDtoShort> getShortByName(@RequestParam("name") String name) {
        TeamDtoShort teamDtoShort;
        try {
            teamDtoShort = teamService.getShortByName(name);
        } catch (ServiceException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(teamDtoShort);
    }

    @GetMapping
    public String getAll(ModelMap modelMap) {
        try {
            modelMap.addAttribute("teams", teamService.getAll());
        } catch (ServiceException e) {
            modelMap.addAttribute("teams", Collections.EMPTY_LIST);
        }
        try {
            modelMap.addAttribute("currentUser", userService.getByAuth());
        } catch (ServiceException e) {
            return "error";
        }
        return "teams";
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TeamDto> add(@RequestBody NewTeamDto teamDto) {
        TeamDto newTeamDto;
        try {
            newTeamDto = teamService.add(teamDto);
        } catch (ServiceException e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(newTeamDto);
    }

    @PostMapping(value = "/edit", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity edit(@RequestBody TeamDto teamDto) {
        try {
            teamService.edit(teamDto);
        } catch (ServiceException e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity edit(@PathVariable("id") UUID id) {
        try {
            teamService.delete(id);
        } catch (ServiceException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
