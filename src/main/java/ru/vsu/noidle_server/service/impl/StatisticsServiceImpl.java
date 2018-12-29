package ru.vsu.noidle_server.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.vsu.noidle_server.exception.ServiceException;
import ru.vsu.noidle_server.model.StatisticsSubType;
import ru.vsu.noidle_server.model.StatisticsType;
import ru.vsu.noidle_server.model.domain.StatisticsEntity;
import ru.vsu.noidle_server.model.domain.UserEntity;
import ru.vsu.noidle_server.model.dto.LanguageStatisticsDto;
import ru.vsu.noidle_server.model.dto.StatisticsDto;
import ru.vsu.noidle_server.model.mapper.CycleAvoidingMappingContext;
import ru.vsu.noidle_server.model.mapper.DataMapper;
import ru.vsu.noidle_server.model.repository.StatisticsRepository;
import ru.vsu.noidle_server.service.NotificationService;
import ru.vsu.noidle_server.service.StatisticsService;
import ru.vsu.noidle_server.service.UserService;

import java.time.OffsetDateTime;
import java.util.*;

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
    public Map<String, String> getAll() {
        UserEntity userEntity;
        try {
            userEntity = userService.getEntityByAuth();
        } catch (ServiceException e) {
            return Collections.emptyMap();
        }
        OffsetDateTime now = OffsetDateTime.now();
        return dataMapper.toDtosDashboard(statisticsRepository
                .getAllByUserIdAndDateGreaterThanEqual(
                        userEntity.getId(),
                        OffsetDateTime.of(
                                now.getYear(), now.getMonth().getValue(), now.getDayOfMonth() - 1,
                                0, 0, 0, 0, now.getOffset()
                        )
                ));
    }

    @Override
    public Map<String, Long> getKeys() {
        UserEntity userEntity;
        try {
            userEntity = userService.getEntityByAuth();
        } catch (ServiceException e) {
            return Collections.emptyMap();
        }
        OffsetDateTime now = OffsetDateTime.now();
        return dataMapper.toDtosKeys(statisticsRepository
                .getAllByUserIdAndTypeAndSubTypeAndDateGreaterThanEqual(
                        userEntity.getId(),
                        StatisticsType.SYMBOL,
                        StatisticsSubType.SINGLE_KEY,
                        OffsetDateTime.of(
                                now.getYear(), now.getMonth().getValue(), now.getDayOfMonth() - 1,
                                0, 0, 0, 0, now.getOffset()
                        )
                )
        );
    }

    @Override
    public Set<LanguageStatisticsDto> getLanguages() {
        UserEntity userEntity;
        try {
            userEntity = userService.getEntityByAuth();
        } catch (ServiceException e) {
            return Collections.emptySet();
        }
        OffsetDateTime now = OffsetDateTime.now();
        return dataMapper.toDtosLanguages(statisticsRepository
                .getAllByUserIdAndTypeInAndSubTypeAndDateGreaterThanEqual(
                        userEntity.getId(),
                        new StatisticsType[]{StatisticsType.LANG_TIME, StatisticsType.LANG_SYMBOL},
                        StatisticsSubType.PER_DAY,
                        OffsetDateTime.of(
                                now.getYear(), now.getMonth().getValue(), now.getDayOfMonth() - 1,
                                0, 0, 0, 0, now.getOffset()
                        )
                )
        );
    }
}
