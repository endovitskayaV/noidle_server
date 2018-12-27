package ru.vsu.noidle_server.service;

import ru.vsu.noidle_server.exception.ServiceException;
import ru.vsu.noidle_server.model.dto.StatisticsDto;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface StatisticsService {

    void save(List<StatisticsDto> statistics, UUID userId, UUID teamId) throws ServiceException;

    Map<String, Long> getAll();

}
