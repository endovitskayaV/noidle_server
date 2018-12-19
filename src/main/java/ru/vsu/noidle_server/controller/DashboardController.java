package ru.vsu.noidle_server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vsu.noidle_server.service.StatisticsService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dashboard")
public class DashboardController {
    private StatisticsService statisticsService;

    @GetMapping
    public String getAll(ModelMap modelMap) {
        //modelMap.addAttribute("statistics", statisticsService.getAll());
        return "dashboard";
    }
}
