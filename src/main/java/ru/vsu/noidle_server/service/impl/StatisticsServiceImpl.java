package ru.vsu.noidle_server.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.vsu.noidle_server.exception.ServiceException;
import ru.vsu.noidle_server.model.domain.StatisticsEntity;
import ru.vsu.noidle_server.model.domain.UserEntity;
import ru.vsu.noidle_server.model.dto.StatisticsDto;
import ru.vsu.noidle_server.model.mapper.CycleAvoidingMappingContext;
import ru.vsu.noidle_server.model.mapper.DataMapper;
import ru.vsu.noidle_server.model.repository.StatisticsRepository;
import ru.vsu.noidle_server.service.NotificationService;
import ru.vsu.noidle_server.service.StatisticsService;
import ru.vsu.noidle_server.service.UserService;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatisticsServiceImpl implements StatisticsService {

    private final StatisticsRepository statisticsRepository;
    private final NotificationService notificationService;
    private final UserService userService;
    private final DataMapper dataMapper;

    @Override
    public void save(List<StatisticsDto> statistics, UUID userId, UUID teamId) throws ServiceException {

        UserEntity user = userService.getEntityById(userId);

        statistics.forEach(statisticsDto -> {

            StatisticsEntity dbEntity = statisticsRepository.getByTypeAndSubTypeAndExtraValueAndUserId(
                    statisticsDto.getType(),
                    statisticsDto.getSubType(),
                    statisticsDto.getExtraValue(),
                    userId);

            boolean canSave;

            if (dbEntity != null) { //not new statistics
                statisticsDto.setId(dbEntity.getId());

                canSave = (dbEntity.getDate().isBefore(statisticsDto.getDate())) &&
                        (dbEntity.getValue() < statisticsDto.getValue());
            } else { //new statistics
                canSave = true;
            }

            if (canSave) {
                StatisticsEntity statisticsEntity = statisticsRepository.save(
                        dataMapper.toEntity(statisticsDto, user, new CycleAvoidingMappingContext())
                );
                log.info("Saved {}", statisticsEntity);
            }
        });

        notificationService.setNotifications(userId, teamId);
    }

    @Override
    public Map<String, Long> getAll() {
        UserEntity userEntity;
        try {
            userEntity = userService.getEntityByAuth();
        } catch (ServiceException e) {
            return Collections.emptyMap();
        }
        return dataMapper.toDtosDashboard(statisticsRepository
                .getAllByUserIdAndDate(userEntity.getId(), OffsetDateTime.now()));
    }
}
