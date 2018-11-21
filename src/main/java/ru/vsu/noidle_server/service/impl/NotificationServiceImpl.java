package ru.vsu.noidle_server.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.vsu.noidle_server.exception.ServiceException;
import ru.vsu.noidle_server.model.domain.RequirementEntity;
import ru.vsu.noidle_server.model.domain.UserEntity;
import ru.vsu.noidle_server.model.dto.AchievementForNotification;
import ru.vsu.noidle_server.model.dto.NotificationDto;
import ru.vsu.noidle_server.model.dto.UserDtoForNotification;
import ru.vsu.noidle_server.model.mapper.LevelMapper;
import ru.vsu.noidle_server.model.repository.LevelRepository;
import ru.vsu.noidle_server.model.repository.RequirementRepository;
import ru.vsu.noidle_server.model.repository.UserRepository;
import ru.vsu.noidle_server.service.NotificationService;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    private final RequirementRepository requirementRepository;
    private final LevelRepository levelRepository;
    private final UserRepository userRepository;
    private final LevelMapper levelMapper;

    @Override
    public NotificationDto get(UUID userId) throws ServiceException {
        UserEntity user;
        try {
            user = userRepository.getOne(userId);
        } catch (EntityNotFoundException e) {
            throw new ServiceException(e);
        }
        List<RequirementEntity> requirements = requirementRepository.getAllByLevelOrder(user.getLevel().getOrder() + 1);

        boolean levelAchieved = requirements.stream().allMatch(requirement -> requirement.anyFits(user.getAchievements()));
        if (levelAchieved) {
            user.setLevel(levelRepository.getByOrder(user.getLevel().getOrder() + 1));
            userRepository.save(user);
            return formNotification(user, requirements);
        } else {
            return null;
        }
    }

    private NotificationDto formNotification(UserEntity user, List<RequirementEntity> requirements) {
        return new NotificationDto(
                levelMapper.toDto(user.getLevel()),
                new UserDtoForNotification(user.getEmail(), user.getName()),
                AchievementForNotification.fromRequirements(requirements)
        );
    }
}
