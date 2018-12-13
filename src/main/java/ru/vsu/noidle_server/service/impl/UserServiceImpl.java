package ru.vsu.noidle_server.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;
import ru.vsu.noidle_server.exception.ServiceException;
import ru.vsu.noidle_server.model.domain.TeamEntity;
import ru.vsu.noidle_server.model.domain.UserEntity;
import ru.vsu.noidle_server.model.dto.UserDto;
import ru.vsu.noidle_server.model.mapper.CycleAvoidingMappingContext;
import ru.vsu.noidle_server.model.mapper.DataMapper;
import ru.vsu.noidle_server.model.repository.UserRepository;
import ru.vsu.noidle_server.service.TeamService;
import ru.vsu.noidle_server.service.UserService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final DataMapper dataMapper;
    private final TeamService teamService;

    @Override
    public UserEntity getEntityById(UUID id) throws ServiceException {
        UserEntity user = userRepository.findById(id).orElse(null);
        if (user == null) {
            log.info("Unable to find user with id " + id);
            throw new ServiceException("Unable to find user with id " + id);
        }
        return user;
    }

    @Override
    public void save(UserEntity userEntity) {
        userRepository.save(userEntity);
        log.info("Saved {}", userEntity);
    }

    @Override
    public UserDto getById(UUID id) throws ServiceException {
        return dataMapper.toDto(getEntityById(id), new CycleAvoidingMappingContext());
    }

    public UserDto save(OAuth2Authentication user) {
        UserEntity userEntity = dataMapper.toEntity(user);
        UserEntity existingUser = userRepository.findByEmail(userEntity.getEmail());
        if (existingUser != null) {
            userEntity.setId(existingUser.getId());
        }
        save(userEntity);
        return dataMapper.toDto(userEntity, new CycleAvoidingMappingContext());
    }

    @Override
    public UserDto getDto(OAuth2Authentication user) {
        return dataMapper.toDto(
                userRepository.findByEmail(DataMapper.getEmail(user)),
                new CycleAvoidingMappingContext());
    }

    @Override
    public void addTeam(UUID userId, UUID teamId) throws ServiceException {
        TeamEntity teamEntity = teamService.getEntityById(teamId);
        UserEntity userEntity = getEntityById(userId);

        userEntity.addTeam(teamEntity);
        userRepository.save(userEntity);
    }
}
