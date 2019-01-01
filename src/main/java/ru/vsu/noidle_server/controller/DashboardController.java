package ru.vsu.noidle_server.controller;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.vsu.noidle_server.exception.ServiceException;
import ru.vsu.noidle_server.model.dto.LanguageStatisticsDto;
import ru.vsu.noidle_server.model.dto.TeamDto;
import ru.vsu.noidle_server.service.StatisticsService;
import ru.vsu.noidle_server.service.TeamService;
import ru.vsu.noidle_server.utils.TimeUtils;

import java.time.OffsetDateTime;
import java.util.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dashboard")
public class DashboardController {
    private final StatisticsService statisticsService;
    private final TeamService teamService;

    @GetMapping
    public String getAll(@RequestParam(name = "date", required = false) String date,
                         @RequestParam(name = "teamId", required = false) UUID teamId,
                         ModelMap modelMap) {
        OffsetDateTime realDate = TimeUtils.toOffsetDateTime(date);
        String outputDate;
        if (date == null) {
            modelMap.addAttribute("overallSelected", true);
            outputDate = TimeUtils.toDMYYYFormat(OffsetDateTime.now());
        } else if (realDate == null) {
            outputDate = date;
        } else {
            outputDate = TimeUtils.toDMYYYFormat(realDate);
        }
        setModel(modelMap, statisticsService.getAll(realDate, teamId), statisticsService.getKeys(realDate, teamId),
                statisticsService.getLanguages(realDate, teamId),
                teamId, outputDate);
        return "dashboard";
    }

    private void setModel(@NotNull ModelMap modelMap, Map<String, String> statistics,
                          Map<String, Long> keys, Set<LanguageStatisticsDto> languages,
                          UUID selectedTeamId, String selectedDate) {
        modelMap.addAttribute("statistics", statistics);
        modelMap.addAttribute("keys", keys);
        modelMap.addAttribute("languages", languages);
        modelMap.addAttribute("selectedTeamId", selectedTeamId);
        modelMap.addAttribute("selectedDate", selectedDate);

        List<TeamDto> teams = new ArrayList<>();
        try {
            teams = teamService.getAll();
        } catch (ServiceException e) {
        }
        modelMap.addAttribute("teams", teams);
    }
}
