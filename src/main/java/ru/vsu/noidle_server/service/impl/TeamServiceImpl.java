package ru.vsu.noidle_server.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.vsu.noidle_server.exception.ServiceException;
import ru.vsu.noidle_server.model.domain.TeamEntity;
import ru.vsu.noidle_server.model.dto.TeamDto;
import ru.vsu.noidle_server.model.mapper.CycleAvoidingMappingContext;
import ru.vsu.noidle_server.model.mapper.DataMapper;
import ru.vsu.noidle_server.model.repository.TeamRepository;
import ru.vsu.noidle_server.service.TeamService;

import java.util.UUID;

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

    @Override
    public TeamEntity getEntityById(UUID id) throws ServiceException {
        TeamEntity team = teamRepository.findById(id).orElse(null);
        if (team == null) {
            log.info("Unable to find team with id " + id);
            throw new ServiceException("Unable to find team with id " + id);
        }
        return team;
    }

    @Override
    public TeamDto getById(UUID id) {
        return dataMapper.toDto(
                teamRepository.findById(id).orElse(null),
                new CycleAvoidingMappingContext()
        );
    }

    @Override
    public TeamDto getByIdOrName(String idOrName) {
        return dataMapper.toDto(
                getEntityByIdOrName(idOrName),
                new CycleAvoidingMappingContext()
        );
    }

    @Override
    public TeamEntity getEntityByIdOrName(String idOrName) {
        TeamEntity teamEntity;
        try {
            UUID id = UUID.fromString(idOrName);
            teamEntity = teamRepository.findById(id).orElse(null);
        } catch (IllegalArgumentException e) {
            teamEntity = teamRepository.getByName(idOrName);
        }
        return teamEntity;
    }
}
