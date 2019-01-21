package ru.vsu.noidle_server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vsu.noidle_server.Constants;
import ru.vsu.noidle_server.exception.ServiceException;
import ru.vsu.noidle_server.model.dto.UserDto;
import ru.vsu.noidle_server.service.AchievementService;
import ru.vsu.noidle_server.service.UserService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/achievements")
public class AchievementController {
    private final AchievementService achievementService;
    private final UserService userService;

    @GetMapping({"", "/{userName}"})
    public String getAll(ModelMap modelMap, @PathVariable(name = "userName", required = false) String userName) {
        if (userName != null) {
            userName = userName.replace(Constants.SPACE_REPLACEMENT, Constants.SPACE);
            try {
                UserDto user = userService.getByName(userName);
                if (!userService.areTeammates(user)) {
                    return "error";
                } else {
                    modelMap.addAttribute("user", user);
                }
            } catch (ServiceException e) {
                return "error";
            }
        }
        modelMap.addAttribute("levels", achievementService.getLevels(userName));
        modelMap.addAttribute("extras", achievementService.getExtras(userName));
        modelMap.addAttribute("teams", achievementService.getTeams(userName));
        return "achievements";
    }
}
