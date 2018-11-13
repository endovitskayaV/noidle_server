package ru.vsu.noidle_server.service;

import ru.vsu.noidle_server.model.dto.AchievementDto;

import java.util.List;

public interface AchievementService {

   void save( List<AchievementDto> achievementDto);
}
