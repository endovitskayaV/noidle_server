package ru.vsu.noidle_server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vsu.noidle_server.exception.ServiceException;
import ru.vsu.noidle_server.model.dto.TeamDto;
import ru.vsu.noidle_server.model.dto.TeamDtoShort;
import ru.vsu.noidle_server.service.TeamService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/teams")
public class TeamController {
    private final TeamService teamService;

    @GetMapping("/{id}")
    public ResponseEntity<TeamDto> getById(@PathVariable UUID id) {
        TeamDto teamDto;
        try {
            teamDto = teamService.getById(id);
        } catch (ServiceException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(teamDto);
    }

    @GetMapping("/short")
    public ResponseEntity<TeamDtoShort> getShortByName(@RequestParam("name") String name) {
        TeamDtoShort teamDtoShort;
        try {
            teamDtoShort = teamService.getShortByName(name);
        } catch (ServiceException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(teamDtoShort);
    }

    //TODO: add validating
    @PostMapping("/add")
    public ResponseEntity<TeamDto> save(TeamDto teamDto) {
        return ResponseEntity.ok(teamService.save(teamDto));
    }
}
