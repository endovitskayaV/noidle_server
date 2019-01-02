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
                         @RequestParam(name = "startDate", required = false) String startDate,
                         @RequestParam(name = "endDate", required = false) String endDate,
                         ModelMap modelMap) {
        modelMap.addAttribute("overallSelected", true);
        //---------------------------set outputDate---------------------------//
        OffsetDateTime realDate = TimeUtils.toOffsetDateTime(date);
        String outputDate;
        if (date == null) {
            //modelMap.addAttribute("overallSelected", true);
            outputDate = TimeUtils.toDMYYYFormat(OffsetDateTime.now());
        } else if (realDate == null) {
            outputDate = date;
        } else {
            outputDate = TimeUtils.toDMYYYFormat(realDate);
        }
        //-----------------------------------------------------------------//

        if ((startDate == null && endDate != null) || (startDate != null && endDate == null) ||
                !TimeUtils.canParseToOffsetDateTimeOrNull(startDate) ||
                !TimeUtils.canParseToOffsetDateTimeOrNull(endDate)) {
            setModel(modelMap, null, null, null, teamId, outputDate, startDate, endDate);
        } else if (startDate != null) { //&& endDate != null will be true by prev condition
            OffsetDateTime start = TimeUtils.toOffsetDateTime(startDate);
            OffsetDateTime end = TimeUtils.toOffsetDateTime(endDate);
            setModel(modelMap,
                    statisticsService.getAll(start, end, teamId),
                    statisticsService.getKeys(start, end, teamId),
                    statisticsService.getLanguages(start, end, teamId),
                    teamId, outputDate, startDate, endDate);
        } else {
            setModel(modelMap, statisticsService.getAll(realDate, teamId), statisticsService.getKeys(realDate, teamId),
                    statisticsService.getLanguages(realDate, teamId),
                    teamId, outputDate, startDate, endDate);
        }
        return "dashboard";
    }

    private void setModel(@NotNull ModelMap modelMap, Map<String, String> statistics,
                          Map<String, Long> keys, Set<LanguageStatisticsDto> languages,
                          UUID selectedTeamId, String selectedDate, String selectedStartDate, String selectedEndDate) {
        modelMap.addAttribute("statistics", statistics);
        modelMap.addAttribute("keys", keys);
        modelMap.addAttribute("languages", languages);
        modelMap.addAttribute("selectedTeamId", selectedTeamId);
        modelMap.addAttribute("selectedDate", selectedDate);
        modelMap.addAttribute("selectedStartDate", selectedStartDate);
        modelMap.addAttribute("selectedEndDate", selectedEndDate);
        List<TeamDto> teams = new ArrayList<>();
        try {
            teams = teamService.getAll();
        } catch (ServiceException e) {
        }
        modelMap.addAttribute("teams", teams);
    }
}
