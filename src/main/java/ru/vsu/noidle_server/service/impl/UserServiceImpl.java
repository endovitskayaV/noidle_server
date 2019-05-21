package ru.vsu.noidle_server.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;
import ru.vsu.noidle_server.exception.ServiceException;
import ru.vsu.noidle_server.model.domain.TeamEntity;
import ru.vsu.noidle_server.model.domain.UserEntity;
import ru.vsu.noidle_server.model.dto.TeamDto;
import ru.vsu.noidle_server.model.dto.UserDto;
import ru.vsu.noidle_server.model.mapper.CycleAvoidingMappingContext;
import ru.vsu.noidle_server.model.mapper.DataMapper;
import ru.vsu.noidle_server.model.repository.UserRepository;
import ru.vsu.noidle_server.service.TeamService;
import ru.vsu.noidle_server.service.UserService;
import ru.vsu.noidle_server.utils.AuthUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final DataMapper dataMapper;

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
    public UserEntity getEntityByName(String name) throws ServiceException {
        UserEntity user = userRepository.findByName(name);
        if (user == null) {
            log.info("Unable to find user with name " + name);
            throw new ServiceException("Unable to find user with name " + name);
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

    @Override
    public UserDto getByName(String name) throws ServiceException {
        UserEntity user = userRepository.findByName(name);
        if (user == null) {
            log.info("Unable to find user with name " + name);
            throw new ServiceException("Unable to find user with name " + name);
        }
        return dataMapper.toDto(user, new CycleAvoidingMappingContext());
    }

    @Override
    public UserDto getByAuth() throws ServiceException {
        return dataMapper.toDto(getEntityByAuth(), new CycleAvoidingMappingContext());
    }

    @Override
    public UserEntity getEntityByAuth() throws ServiceException {
        Authentication user = AuthUtils.getUser();
        if (user == null) {
            log.info("Unable to find auth user");
            throw new ServiceException("Unable to find auth user");
        }
        String email = dataMapper.getEmail((LinkedHashMap<String, String>) ((OAuth2Authentication) user).getUserAuthentication().getDetails());
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null) {
            throw new ServiceException("Unable to find auth user");
        } else {
            return userEntity;
        }
    }

    public UserDto save(OAuth2Authentication user) throws ServiceException{
        UserEntity userEntity = dataMapper.toEntity(user);
        UserEntity existingUser = userRepository.findByEmail(userEntity.getEmail());
        if (existingUser != null) {
            existingUser.setName(userEntity.getName());
            existingUser.setEmail(userEntity.getEmail());
            existingUser.setPhoto(userEntity.getPhoto());

            save(existingUser);
            return dataMapper.toDto(existingUser, new CycleAvoidingMappingContext());
        } else {
            save(userEntity);
            return dataMapper.toDto(userEntity, new CycleAvoidingMappingContext());
        }

    }

    @Override
    public boolean areTeammates(UserDto user1, UserDto user2) {
        try {
            return getEntityById(user1.getId()).isTeammateWith(getEntityById(user2.getId()));
        } catch (ServiceException e) {
            return false;
        }
    }

    @Override
    public boolean areTeammates(UserDto user1) {
        try {
            return areTeammates(user1, getByAuth());
        } catch (ServiceException e) {
            return false;
        }
    }

    @Override
    public UserDto getDto(OAuth2Authentication user) throws ServiceException {
        return dataMapper.toDto(
                userRepository.findByEmail(dataMapper.getEmail(user)),
                new CycleAvoidingMappingContext());
    }
}
