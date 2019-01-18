package ru.vsu.noidle_server.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;
import ru.vsu.noidle_server.Constants;
import ru.vsu.noidle_server.exception.ServiceException;
import ru.vsu.noidle_server.model.AchievementType;
import ru.vsu.noidle_server.model.StatisticsType;
import ru.vsu.noidle_server.model.domain.RequirementEntity;
import ru.vsu.noidle_server.model.domain.TeamEntity;
import ru.vsu.noidle_server.model.domain.UserEntity;
import ru.vsu.noidle_server.model.dto.AchievementDashboardDto;
import ru.vsu.noidle_server.model.mapper.DataMapper;
import ru.vsu.noidle_server.model.repository.AchievementRepository;
import ru.vsu.noidle_server.model.repository.RequirementRepository;
import ru.vsu.noidle_server.service.AchievementService;
import ru.vsu.noidle_server.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AchievementServiceImpl implements AchievementService {

    private final AchievementRepository achievementRepository;
    private final RequirementRepository requirementRepository;
    private final UserService userService;
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

    @Nullable
    private List<AchievementDashboardDto> doGetAchievementDashboardDtos(@NotNull AchievementType type) {
        List<AchievementDashboardDto> achievements = new ArrayList<>();
        UserEntity user;
        try {
            user = userService.getEntityByAuth();
        } catch (ServiceException e) {
            return null;
        }

        achievementRepository.getAllByType(type)
                .forEach(achievement -> {
                    List<RequirementEntity> requirements = requirementRepository.getAllByAchievementId(achievement.getId());
                    List<String> achievedComments = new ArrayList<>();
                    if (user.hasAchievement(achievement.getId())) {
                        if (AchievementType.TEAM.equals(achievement.getType())) {
                            achievedComments.addAll(
                                    user.getTeamsByAchievementId(achievement.getId()).stream()
                                            .map(TeamEntity::getName).collect(Collectors.toList()));
                        } else {
                            achievedComments.add(Constants.ACHIEVED);
                        }
                    }
                    achievements.add(new AchievementDashboardDto(
                            achievement.getLevelNumber(),
                            achievement.getName(),
                            dataMapper.toDashboardRequirements(requirements),
                            achievedComments));
                });
        return achievements.size() == 0 ? null : achievements;
    }
}
