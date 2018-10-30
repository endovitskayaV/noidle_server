package ru.vsu.noidle_server.service;

import ru.vsu.noidle_server.model.UserEntity;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserEntity getById(UUID id);

    List<UserEntity> getAll();

    UserEntity saveDataEntity(UserEntity dataEntity);
}
