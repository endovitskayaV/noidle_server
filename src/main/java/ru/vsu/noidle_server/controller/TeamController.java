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

    @GetMapping("/add")
    public String add(ModelMap modelMap) {
        modelMap.addAttribute("team", TeamDto.newInstance());
        return "add_team";
    }

    @PostMapping(value = "/add")
    public String add(TeamDto teamDto) {
        TeamDto newTeam = teamService.save(teamDto);
        return "/teams/" + newTeam.getId();
    }

    @GetMapping("/edit/{id}")
    public String edit(ModelMap modelMap, @PathVariable UUID id) {
        TeamDto teamDto;
        try {
            teamDto = teamService.getById(id);
            modelMap.addAttribute("team", teamDto);
            return "edit_team";
        } catch (ServiceException e) {
            return errorResponse(modelMap);
        }
    }

    @PostMapping(value = "/edit")
    public ResponseEntity edit(TeamDto teamDto) {
        teamService.save(teamDto);
        return ResponseEntity.noContent().build();
    }

    private String errorResponse(ModelMap modelMap) {
        modelMap.addAttribute("error", "Team not found");
        return "error";
    }
}
