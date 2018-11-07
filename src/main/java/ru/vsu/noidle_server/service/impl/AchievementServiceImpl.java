package ru.vsu.noidle_server.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.vsu.noidle_server.model.domain.AchievementEntity;
import ru.vsu.noidle_server.model.dto.AchievementDto;
import ru.vsu.noidle_server.model.mapper.CycleAvoidingMappingContext;
import ru.vsu.noidle_server.model.mapper.UserMapper;
import ru.vsu.noidle_server.model.repository.AchievementRepository;
import ru.vsu.noidle_server.service.AchievementService;

@Service
@RequiredArgsConstructor
@Slf4j
public class AchievementServiceImpl implements AchievementService {

    private final AchievementRepository achievementRepository;
    private final UserMapper userMapper;

    @Override
    public AchievementDto save(AchievementDto achievementDto) {
        AchievementEntity dbEntity=achievementRepository.getByNameAndUserId(achievementDto.getName(), achievementDto.getUserId());
         if (dbEntity!=null){
             achievementDto.setId(dbEntity.getId());
         }

        AchievementEntity achievementEntity = achievementRepository.save(
                userMapper.toEntity(achievementDto, new CycleAvoidingMappingContext())
        );
        log.info("Saved {}", achievementEntity);
        return userMapper.toDto(achievementEntity, new CycleAvoidingMappingContext());
    }
}
