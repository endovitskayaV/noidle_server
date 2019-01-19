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
import java.util.UUID;
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
    public List<AchievementDashboardDto> getLevels(String userName) {
        return doGetAchievementDashboardDtos(userName, AchievementType.LEVEL);
    }

    @Override
    public List<AchievementDashboardDto> getExtras(String userName) {
        return doGetAchievementDashboardDtos(userName, AchievementType.EXTRA);
    }

    @Override
    public List<AchievementDashboardDto> getTeams(String userName) {
        return doGetAchievementDashboardDtos(userName, AchievementType.TEAM);
    }

    @Nullable
    private List<AchievementDashboardDto> doGetAchievementDashboardDtos(@Nullable String userName, @NotNull AchievementType type) {
        List<AchievementDashboardDto> achievements = new ArrayList<>();
        UserEntity user;
        try {
            user = userName == null ?
                    userService.getEntityByAuth() :
                    userService.getEntityByName(userName);
        } catch (ServiceException e) {
            return null;
        }

        achievementRepository.getAllByType(type)
                .forEach(achievement -> {

                    //add achievement only
                    // if it`s current user
                    // or if it is team member and achieved
                    if (userName == null || user.hasAchievement(achievement.getId())) {
                        List<RequirementEntity> requirements =
                                requirementRepository.getAllByAchievementId(achievement.getId());
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
                    }
                });
        return achievements.size() == 0 ? null : achievements;
    }
}
