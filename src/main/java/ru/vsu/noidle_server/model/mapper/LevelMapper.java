package ru.vsu.noidle_server.model.mapper;

import org.mapstruct.Mapper;
import ru.vsu.noidle_server.model.domain.LevelEntity;
import ru.vsu.noidle_server.model.dto.LevelDto;

@Mapper(componentModel = "spring")
public interface LevelMapper {

    LevelDto toDto (LevelEntity levelEntity);
}
