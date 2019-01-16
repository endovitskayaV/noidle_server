package ru.vsu.noidle_server.service;

import ru.vsu.noidle_server.model.dto.AchievementDashboardDto;

import java.util.List;

public interface AchievementService {
    List<AchievementDashboardDto> getLevels();

    List<AchievementDashboardDto> getExtras();

    List<AchievementDashboardDto> getTeams();
}
