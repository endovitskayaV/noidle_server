package ru.vsu.noidle_server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.vsu.noidle_server.exception.ServiceException;
import ru.vsu.noidle_server.model.dto.TeamDto;
import ru.vsu.noidle_server.model.dto.TeamDtoShort;
import ru.vsu.noidle_server.service.TeamService;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/teams")
public class TeamController {
    private final TeamService teamService;

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
        modelMap.addAttribute("teams", teamService.getAll());
        return "teams";
    }

    @PostMapping(value = "/add")
    public ResponseEntity add(TeamDto teamDto) {
        teamService.save(teamDto);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/edit")
    public ResponseEntity edit(TeamDto teamDto) {
        teamService.save(teamDto);
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
