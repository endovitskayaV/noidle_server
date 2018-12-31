package ru.vsu.noidle_server.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.vsu.noidle_server.Constants;
import ru.vsu.noidle_server.exception.ServiceException;
import ru.vsu.noidle_server.model.domain.TeamEntity;
import ru.vsu.noidle_server.model.domain.UserEntity;
import ru.vsu.noidle_server.model.dto.NewTeamDto;
import ru.vsu.noidle_server.model.dto.TeamDto;
import ru.vsu.noidle_server.model.dto.TeamDtoShort;
import ru.vsu.noidle_server.model.mapper.CycleAvoidingMappingContext;
import ru.vsu.noidle_server.model.mapper.DataMapper;
import ru.vsu.noidle_server.model.repository.TeamRepository;
import ru.vsu.noidle_server.service.TeamService;
import ru.vsu.noidle_server.service.UserService;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final UserService userService;
    private final DataMapper dataMapper;

    @Override
    public TeamDto add(NewTeamDto teamDto) throws ServiceException {
        TeamEntity teamEntity;
        try {
            UserEntity userEntity = userService.getEntityByAuth();
            teamEntity = new TeamEntity(teamDto.getName(), OffsetDateTime.now());
            teamEntity = teamRepository.save(teamEntity);
            userEntity.addTeam(teamEntity);
            userService.save(userEntity);
        } catch (DataIntegrityViolationException e) {
            throw new ServiceException(teamDto.getName() + " team already exists");
        }
        log.info("Saved new team {}", teamEntity);
        return dataMapper.toDto(teamEntity, new CycleAvoidingMappingContext());
    }

    @Override
    public TeamDto edit(TeamDto teamDto) throws ServiceException {
        TeamEntity teamEntity;
        try {
            teamDto.setCreated(OffsetDateTime.now());
            teamEntity = teamRepository.save(dataMapper.toEntity(teamDto, new CycleAvoidingMappingContext()));
        } catch (DataIntegrityViolationException e) {
            throw new ServiceException(teamDto.getName() + " team already exists");
        }
        log.info("Saved team {}", teamEntity);
        return dataMapper.toDto(teamEntity, new CycleAvoidingMappingContext());
    }

    @Override
    public TeamEntity getEntityById(UUID id) throws ServiceException {
        TeamEntity team=null;
        if (id != null) {
            team = teamRepository.findById(id).orElse(null);
        }
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
    public void delete(UUID id) throws ServiceException {
        TeamEntity team = getEntityById(id);
        teamRepository.delete(team);
    }

    @Override
    public List<TeamDto> getAll() throws ServiceException {
        return userService.getEntityByAuth()
                .getTeams().stream()
                .map(teamEntity -> dataMapper.toDto(teamEntity, new CycleAvoidingMappingContext()))
                .collect(Collectors.toList());
    }
}
