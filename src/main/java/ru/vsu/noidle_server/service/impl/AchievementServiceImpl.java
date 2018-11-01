package ru.vsu.noidle_server.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.vsu.noidle_server.model.domain.AchievementEntity;
import ru.vsu.noidle_server.model.dto.AchievementDto;
import ru.vsu.noidle_server.model.mapper.AchievementMapper;
import ru.vsu.noidle_server.model.repository.AchievementRepository;
import ru.vsu.noidle_server.service.AchievementService;

@Service
@RequiredArgsConstructor
@Slf4j
public class AchievementServiceImpl implements AchievementService {

    private final AchievementRepository achievementRepository;
    private final AchievementMapper achievementMapper;

    @Override
    public AchievementDto save(AchievementDto achievementDto) {
        AchievementEntity achievementEntity = achievementRepository.save(
                achievementMapper.toEntity(achievementDto)
        );
        log.info("Saved {}", achievementEntity);
        return achievementMapper.toDto(achievementEntity);
    }
}
