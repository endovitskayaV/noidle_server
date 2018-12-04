package ru.vsu.noidle_server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vsu.noidle_server.model.dto.TeamDto;
import ru.vsu.noidle_server.service.TeamService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/teams")
public class TeamController {
    private final TeamService teamService;

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable UUID id) {
        TeamDto teamDto = teamService.getById(id);
        return teamDto == null ? ResponseEntity.ok().build() : ResponseEntity.ok(teamDto);
    }

    @PostMapping("/add")
    public ResponseEntity<TeamDto> save(TeamDto teamDto) {
        return ResponseEntity.ok(teamService.save(teamDto));
    }
}
