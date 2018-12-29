package ru.vsu.noidle_server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vsu.noidle_server.service.StatisticsService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dashboard")
public class DashboardController {
    private final StatisticsService statisticsService;

    //TODO: add  getting by date and team (+ no team)
    @GetMapping
    public String getAll(ModelMap modelMap) {
        modelMap.addAttribute("statistics", statisticsService.getAll());
        modelMap.addAttribute("keys", statisticsService.getKeys());
        modelMap.addAttribute("languages", statisticsService.getLanguages());
        return "dashboard";
    }
}
