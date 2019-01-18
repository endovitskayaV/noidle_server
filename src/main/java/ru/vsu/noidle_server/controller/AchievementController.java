package ru.vsu.noidle_server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vsu.noidle_server.service.AchievementService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/achievements")
public class AchievementController {
    private final AchievementService achievementService;

    @GetMapping
    public String getAll(ModelMap modelMap) {
        modelMap.addAttribute("levels", achievementService.getLevels());
        modelMap.addAttribute("extras", achievementService.getExtras());
        modelMap.addAttribute("teams", achievementService.getTeams());
        return "achievements";
    }
}
