package ru.vsu.noidle_server.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vsu.noidle_server.exception.ServiceException;
import ru.vsu.noidle_server.model.UpdateRole;
import ru.vsu.noidle_server.model.domain.UserEntity;
import ru.vsu.noidle_server.model.dto.UserDto;
import ru.vsu.noidle_server.model.mapper.CycleAvoidingMappingContext;
import ru.vsu.noidle_server.model.mapper.DataMapper;
import ru.vsu.noidle_server.model.repository.UserRepository;
import ru.vsu.noidle_server.service.UserService;
import ru.vsu.noidle_server.utils.AuthUtils;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final DataMapper dataMapper;
    private final PasswordEncoder passwordEncoder;

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
    public void save(UserEntity userEntity, boolean update) throws ServiceException {
        if (!update) {
            userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));

            if (userRepository.findByEmailOrNameOrId(userEntity.getEmail(), userEntity.getName(), userEntity.getId()) != null) {
               throw new ServiceException("Not unique user: " + dataMapper.toString(userEntity));
            }
        }
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
        String name = user.getName();
        UserEntity userEntity = userRepository.findByName(name);
        if (userEntity == null) {
            throw new ServiceException("Unable to find auth user");
        } else {
            return userEntity;
        }
    }

    @Override
    public boolean anyAdminsExists() {
        List<UserEntity> admins = userRepository.findAllByUpdateRole(UpdateRole.ADMIN);
        return admins != null && !admins.isEmpty();
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void saveUsers(String userData) throws ServiceException {
        List<UserEntity> userEntities = dataMapper.toEntity(userData);
        if (userEntities == null) {
            throw new ServiceException("Error while parsing input string");
        }
        for (UserEntity userEntity : userEntities) {
            save(userEntity, false);
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
    public UserEntity getEntityByName(String name) throws ServiceException {
        UserEntity user = userRepository.findByName(name);
        if (user == null) {
            log.info("Unable to find user with name " + name);
            throw new ServiceException("Unable to find user with name " + name);
        }
        return user;
    }
}
