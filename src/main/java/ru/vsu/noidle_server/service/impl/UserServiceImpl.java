package ru.vsu.noidle_server.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.vsu.noidle_server.model.UserEntity;
import ru.vsu.noidle_server.model.repository.UserRepository;
import ru.vsu.noidle_server.service.UserService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;


    public UserEntity getById(UUID id) {
        return userRepository.getOne(id);
    }

    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }

    public UserEntity saveDataEntity(UserEntity dataEntity) {
        log.info("Saved {}", dataEntity);
        return userRepository.save(dataEntity);
    }
}
