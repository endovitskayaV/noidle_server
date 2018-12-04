package ru.vsu.noidle_server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vsu.noidle_server.model.dto.TeamDto;
import ru.vsu.noidle_server.service.TeamService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/teams")
public class TeamController {
    private final TeamService teamService;

    @PostMapping("/add")
    public ResponseEntity<TeamDto> save(TeamDto teamDto) {
        return ResponseEntity.ok(teamService.save(teamDto));
    }
}
