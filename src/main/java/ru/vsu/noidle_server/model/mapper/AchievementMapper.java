package ru.vsu.noidle_server.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.vsu.noidle_server.model.domain.AchievementEntity;
import ru.vsu.noidle_server.model.dto.AchievementDto;

@Mapper(componentModel = "spring")
public interface AchievementMapper {

    @Mapping(source = "achievementDto.userId", target = "user.id")
    AchievementEntity toEntity(AchievementDto achievementDto);

    @Mapping(source = "achievementEntity.user.id", target = "userId")
    AchievementDto toDto(AchievementEntity achievementEntity);
}
