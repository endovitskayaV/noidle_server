package ru.vsu.noidle_server.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.vsu.noidle_server.model.AchievementType;
import ru.vsu.noidle_server.model.domain.RequirementEntity;
import ru.vsu.noidle_server.model.dto.AchievementDashboardDto;
import ru.vsu.noidle_server.model.mapper.DataMapper;
import ru.vsu.noidle_server.model.repository.AchievementRepository;
import ru.vsu.noidle_server.model.repository.RequirementRepository;
import ru.vsu.noidle_server.service.AchievementService;
import ru.vsu.noidle_server.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AchievementServiceImpl implements AchievementService {

    private final AchievementRepository achievementRepository;
    private final RequirementRepository requirementRepository;
    private final DataMapper dataMapper;

    @Override
    public List<AchievementDashboardDto> getLevels() {
        return doGetAchievementDashboardDtos(AchievementType.LEVEL);
    }

    @Override
    public List<AchievementDashboardDto> getExtras() {
        return doGetAchievementDashboardDtos(AchievementType.EXTRA);
    }

    @Override
    public List<AchievementDashboardDto> getTeams() {
        return doGetAchievementDashboardDtos(AchievementType.TEAM);
    }

    private List<AchievementDashboardDto> doGetAchievementDashboardDtos(AchievementType type) {
        List<AchievementDashboardDto> achievements = new ArrayList<>();
        achievementRepository.getAllByType(type)
                .forEach(achievement -> {
                    List<RequirementEntity> requirements = requirementRepository.getAllByAchievementId(achievement.getId());
                    achievements.add(new AchievementDashboardDto(
                            achievement.getLevelNumber(),
                            achievement.getName(),
                            dataMapper.toDashboardRequirements(requirements)));
                });
        return achievements.size() == 0 ? null : achievements;
    }
}
