package ru.vsu.noidle_server.service;

import ru.vsu.noidle_server.model.dto.AchievementDashboardDto;

import java.util.List;
import java.util.UUID;

public interface AchievementService {
    List<AchievementDashboardDto> getLevels(String userName);

    List<AchievementDashboardDto> getExtras(String userName);

    List<AchievementDashboardDto> getTeams(String userName);
}
