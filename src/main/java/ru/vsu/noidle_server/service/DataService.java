package ru.vsu.noidle_server.service;

import ru.vsu.noidle_server.model.DataEntity;

import java.util.List;

public interface DataService {

    DataEntity getById(Integer id);

    List<DataEntity> getAll();

    DataEntity saveDataEntity(DataEntity dataEntity);
}
