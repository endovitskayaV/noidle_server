package ru.vsu.noidle_server.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.vsu.noidle_server.model.StatisticsSubType;
import ru.vsu.noidle_server.model.StatisticsType;
import ru.vsu.noidle_server.model.domain.StatisticsEntity;
import ru.vsu.noidle_server.model.domain.StatisticsDashboardEntity;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface StatisticsRepository extends JpaRepository<StatisticsEntity, UUID> {

    StatisticsEntity getByTypeAndSubTypeAndExtraValueAndUserIdAndTeamId
            (StatisticsType statisticsType, StatisticsSubType statisticsSubType,
             String extraValue, UUID userId, UUID teamId);

    List<StatisticsEntity> getAllByUserIdAndSubTypeInAndDateGreaterThanEqualAndTeamId
            (UUID userId, StatisticsSubType[] statisticsSubType, OffsetDateTime date, UUID teamId);

    List<StatisticsEntity> getAllByUserIdAndTypeAndSubTypeAndTeamId
            (UUID userId, StatisticsType statisticsType, StatisticsSubType statisticsSubType, UUID teamId);

    List<StatisticsEntity> getAllByUserIdAndTypeInAndSubTypeAndDateGreaterThanEqualAndTeamId
            (UUID userId, StatisticsType[] statisticsType, StatisticsSubType statisticsSubType, OffsetDateTime date,
             UUID teamId);

    List<StatisticsEntity> getAllByUserIdAndSubTypeInAndTeamId
            (UUID userId, StatisticsSubType[] statisticsSubType, UUID teamId);

    List<StatisticsEntity> getAllByUserIdAndTypeInAndSubTypeAndTeamId
            (UUID userId, StatisticsType[] statisticsType, StatisticsSubType statisticsSubType, UUID teamId);

    @Query(name = "findStatisticsByPeriod", nativeQuery = true)
    List<StatisticsDashboardEntity> findStatisticsByPeriod
            (@Param("userId") UUID userId, @Param("statisticsSubTypes") List<String> statisticsSubTypes,
             @Param("startDate") OffsetDateTime startDate, @Param("endDate") OffsetDateTime endDate,
             @Param("teamId") UUID teamId);

    @Query(name = "findStatisticsLanguagesByPeriod", nativeQuery = true)
    List<StatisticsDashboardEntity> findStatisticsLanguagesByPeriod
            (@Param("userId") UUID userId, @Param("statisticsTypes") List<String> statisticsTypes,
             @Param("statisticsSubType") String statisticsSubType,
             @Param("startDate") OffsetDateTime startDate, @Param("endDate") OffsetDateTime endDate,
             @Param("teamId") UUID teamId);

}
