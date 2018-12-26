package ru.vsu.noidle_server.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.vsu.noidle_server.Constants;
import ru.vsu.noidle_server.exception.ServiceException;
import ru.vsu.noidle_server.model.domain.TeamEntity;
import ru.vsu.noidle_server.model.dto.TeamDto;
import ru.vsu.noidle_server.model.dto.TeamDtoShort;
import ru.vsu.noidle_server.model.mapper.CycleAvoidingMappingContext;
import ru.vsu.noidle_server.model.mapper.DataMapper;
import ru.vsu.noidle_server.model.repository.TeamRepository;
import ru.vsu.noidle_server.service.TeamService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
    public TeamDto getById(UUID id) throws ServiceException {
        return dataMapper.toDto(getEntityById(id), new CycleAvoidingMappingContext()
        );
    }

    @Override
    public TeamDtoShort getShortByName(String name) throws ServiceException {
        name = name.replace(Constants.SPACE_REPLACEMENT, Constants.SPACE);
        TeamDtoShort teamDtoShort = dataMapper.toDtoShort(teamRepository.getByName(name));
        if (teamDtoShort == null) {
            log.info("Unable to find team with name " + name);
            throw new ServiceException("Unable to find team with name " + name);
        }
        return teamDtoShort;
    }

    @Override
    public List<TeamDto> getAll() {
        return teamRepository.findAll().stream().map(teamEntity -> dataMapper.toDto(teamEntity, new CycleAvoidingMappingContext())).collect(Collectors.toList());
    }

    @Override
    public void delete(UUID id) throws ServiceException {
        TeamEntity team=getEntityById(id);
        teamRepository.delete(team);
    }
}
