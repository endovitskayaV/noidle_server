package ru.vsu.noidle_server.service;

import ru.vsu.noidle_server.exception.ServiceException;
import ru.vsu.noidle_server.model.dto.LanguageStatisticsDto;
import ru.vsu.noidle_server.model.dto.StatisticsDto;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public interface StatisticsService {

    void save(List<StatisticsDto> statistics, UUID userId, UUID teamId) throws ServiceException;

    Map<String, String> getAll(OffsetDateTime date, UUID teamId);

    Map<String, Long> getKeys(OffsetDateTime date, UUID teamId);

    Set<LanguageStatisticsDto> getLanguages(OffsetDateTime date, UUID teamId);
}
