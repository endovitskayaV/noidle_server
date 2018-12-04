package ru.vsu.noidle_server.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.vsu.noidle_server.model.domain.TeamEntity;
import ru.vsu.noidle_server.model.dto.TeamDto;
import ru.vsu.noidle_server.model.mapper.CycleAvoidingMappingContext;
import ru.vsu.noidle_server.model.mapper.DataMapper;
import ru.vsu.noidle_server.model.repository.TeamRepository;
import ru.vsu.noidle_server.service.TeamService;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final DataMapper dataMapper;

    @Override
    public TeamDto save(TeamDto teamDto) {
        TeamEntity teamEntity = teamRepository.save(dataMapper.toEntity(teamDto, new CycleAvoidingMappingContext()));
        log.info("Saved new team {}", teamEntity);
        return dataMapper.toDto(teamEntity, new CycleAvoidingMappingContext());
    }
}
