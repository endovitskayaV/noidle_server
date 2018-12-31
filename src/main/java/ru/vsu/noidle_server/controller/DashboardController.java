package ru.vsu.noidle_server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vsu.noidle_server.exception.ServiceException;
import ru.vsu.noidle_server.model.dto.TeamDto;
import ru.vsu.noidle_server.service.StatisticsService;
import ru.vsu.noidle_server.service.TeamService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dashboard")
public class DashboardController {
    private final StatisticsService statisticsService;
    private final TeamService teamService;

    //TODO: add  getting by date and team (+ no team)
    @GetMapping
    public String getAll(ModelMap modelMap) {
        modelMap.addAttribute("statistics", statisticsService.getAll());
        modelMap.addAttribute("keys", statisticsService.getKeys());
        modelMap.addAttribute("languages", statisticsService.getLanguages());
        List<TeamDto> teams=new ArrayList<>();
        try {
            teams=teamService.getAll();
        } catch (ServiceException e) {
        }
        modelMap.addAttribute("teams", teams);
        return "dashboard";
    }
}
