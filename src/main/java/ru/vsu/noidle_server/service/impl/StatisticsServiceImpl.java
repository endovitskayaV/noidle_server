package ru.vsu.noidle_server.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import ru.vsu.noidle_server.exception.ServiceException;
import ru.vsu.noidle_server.model.StatisticsSubType;
import ru.vsu.noidle_server.model.domain.StatisticsEntity;
import ru.vsu.noidle_server.model.domain.TeamEntity;
import ru.vsu.noidle_server.model.domain.UserEntity;
import ru.vsu.noidle_server.model.dto.LanguageStatisticsDto;
import ru.vsu.noidle_server.model.dto.StatisticsDto;
import ru.vsu.noidle_server.model.mapper.CycleAvoidingMappingContext;
import ru.vsu.noidle_server.model.mapper.DataMapper;
import ru.vsu.noidle_server.model.repository.StatisticsRepository;
import ru.vsu.noidle_server.model.repository.TeamRepository;
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
    private final TeamRepository teamRepository;
    private final NotificationService notificationService;
    private final UserService userService;
    private final DataMapper dataMapper;
    private final List<StatisticsSubType> perLifeSubTypes = Arrays.asList(StatisticsSubType.PER_LIFE, StatisticsSubType.CONTINUOUS_PER_LIFE, StatisticsSubType.SINGLE_KEY);

    @Override
    public void save(List<StatisticsDto> statistics, UUID userId, UUID teamId) throws ServiceException {

        UserEntity user = userService.getEntityById(userId);

        statistics.forEach(statisticsDto -> {

            StatisticsEntity dbEntity = statisticsRepository.getByTypeAndSubtypeAndExtravalueAndUserIdAndTeamId(
                    statisticsDto.getType(),
                    statisticsDto.getSubtype(),
                    statisticsDto.getExtravalue(),
                    userId,
                    teamId);

            boolean canSave;

            if (dbEntity != null) { //not new statistics
                statisticsDto.setId(dbEntity.getId());

                if (perLifeSubTypes.contains(statisticsDto.getSubtype())) {
                    canSave = (dbEntity.getValue() < statisticsDto.getValue());
                } else {
                    canSave = (dbEntity.getDate().isBefore(statisticsDto.getDate())) &&
                            (dbEntity.getValue() < statisticsDto.getValue());
                }
            } else { //new statistics
                canSave = true;
            }

            if (canSave) {
                TeamEntity teamEntity = teamId == null ? null : teamRepository.findById(teamId).orElse(null);
                StatisticsEntity statisticsEntity = statisticsRepository.save(
                        dataMapper.toEntity(statisticsDto, user, teamEntity, new CycleAvoidingMappingContext())
                );
                log.info("Saved {}", statisticsEntity);
            }
        });

        notificationService.setNotifications(userId, teamId);
    }

    @Override
    public Map<String, String> getAll(OffsetDateTime date, UUID teamId) {
        UserEntity userEntity;
        try {
            userEntity = userService.getEntityByAuth();
        } catch (ServiceException e) {
            return Collections.emptyMap();
        }

        return dataMapper.toDtosDashboard(
               null
        );
    }

    @Override
    public Map<String, Long> getKeys(OffsetDateTime date, UUID teamId) {
        UserEntity userEntity;
        try {
            userEntity = userService.getEntityByAuth();
        } catch (ServiceException e) {
            return Collections.emptyMap();
        }

        return dataMapper.toDtosKeys(
                null);
    }

    @Override
    public Set<LanguageStatisticsDto> getLanguages(OffsetDateTime date, UUID teamId) {
        UserEntity userEntity;
        try {
            userEntity = userService.getEntityByAuth();
        } catch (ServiceException e) {
            return Collections.emptySet();
        }


        return dataMapper.toDtosLanguages(
             null
        );
    }

    @Override
    public Map<String, String> getAll(@NotNull OffsetDateTime startDate, @NotNull OffsetDateTime endDate, UUID teamId) {
        UserEntity userEntity;
        try {
            userEntity = userService.getEntityByAuth();
        } catch (ServiceException e) {
            return Collections.emptyMap();
        }

        return dataMapper.toDtosDashboard(
                teamId == null ?
                        null :
                        statisticsRepository.findStatisticsByPeriodAndTeam(
                                userEntity.getId(),
                                Arrays.asList(StatisticsSubType.PER_DAY.getShortcut(), StatisticsSubType.CONTINUOUS_PER_DAY.getShortcut()),
                                OffsetDateTime.of(
                                        startDate.getYear(), startDate.getMonth().getValue(), startDate.getDayOfMonth(),
                                        0, 0, 0, 0, startDate.getOffset()
                                ),
                                OffsetDateTime.of(
                                        endDate.getYear(), endDate.getMonth().getValue(), endDate.getDayOfMonth(),
                                        23, 59, 59, 999999999, endDate.getOffset()
                                ),
                                teamId
                        )
        );
    }

    @Override
    public Map<String, Long> getKeys(@NotNull OffsetDateTime startDate, @NotNull OffsetDateTime endDate, UUID teamId) {
        return null;
    }

    @Override
    public Set<LanguageStatisticsDto> getLanguages(@NotNull OffsetDateTime startDate, @NotNull OffsetDateTime endDate, UUID teamId) {
        UserEntity userEntity;
        try {
            userEntity = userService.getEntityByAuth();
        } catch (ServiceException e) {
            return Collections.emptySet();
        }

        return dataMapper.toDtosLanguages(null);
    }
}
