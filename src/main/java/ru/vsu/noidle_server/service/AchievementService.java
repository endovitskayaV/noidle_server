package ru.vsu.noidle_server.service;

import ru.vsu.noidle_server.model.dto.AchievementDto;

import java.util.List;

public interface AchievementService {

    AchievementDto save(AchievementDto achievementDto);

    List<AchievementDto> getNotifications();
}
