package ru.vsu.noidle_server.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.vsu.noidle_server.model.StatisticsSubType;
import ru.vsu.noidle_server.model.StatisticsType;
import ru.vsu.noidle_server.model.domain.StatisticsDashboardEntity;
import ru.vsu.noidle_server.model.domain.StatisticsEntity;

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

    List<StatisticsEntity> getAllByUserIdAndDateGreaterThanEqualAndTeamId
            (UUID userId,  OffsetDateTime date, UUID teamId);

    List<StatisticsEntity> getAllByUserIdAndTypeAndSubTypeAndTeamId
            (UUID userId, StatisticsType statisticsType, StatisticsSubType statisticsSubType, UUID teamId);

    List<StatisticsEntity> getAllByUserIdAndTypeInAndSubTypeAndDateGreaterThanEqualAndTeamId
            (UUID userId, StatisticsType[] statisticsType, StatisticsSubType statisticsSubType, OffsetDateTime date,
             UUID teamId);

    List<StatisticsEntity> getAllByUserIdAndTypeAndSubTypeAndDateGreaterThanEqualAndTeamId
            (UUID userId, StatisticsType statisticsType, StatisticsSubType statisticsSubType, OffsetDateTime date, UUID teamId);

    List<StatisticsEntity> getAllByUserIdAndSubTypeInAndTeamId
            (UUID userId, StatisticsSubType[] statisticsSubType, UUID teamId);

    List<StatisticsEntity> getAllByUserIdAndTypeInAndSubTypeAndTeamId
            (UUID userId, StatisticsType[] statisticsType, StatisticsSubType statisticsSubType, UUID teamId);

    @Query(name = "findStatisticsByPeriodAndTeam", nativeQuery = true)
    List<StatisticsDashboardEntity> findStatisticsByPeriodAndTeam
            (@Param("userId") UUID userId, @Param("statisticsSubTypes") List<String> statisticsSubTypes,
             @Param("startDate") OffsetDateTime startDate, @Param("endDate") OffsetDateTime endDate,
             @Param("teamId") UUID teamId);

    @Query(name = "findStatisticsByPeriodOutOfTeam", nativeQuery = true)
    List<StatisticsDashboardEntity> findStatisticsByPeriodOutOfTeam
            (@Param("userId") UUID userId, @Param("statisticsSubTypes") List<String> statisticsSubTypes,
             @Param("startDate") OffsetDateTime startDate, @Param("endDate") OffsetDateTime endDate);

    @Query(name = "findStatisticsLanguagesByPeriodAndTeam", nativeQuery = true)
    List<StatisticsDashboardEntity> findStatisticsLanguagesByPeriodAndTeam
            (@Param("userId") UUID userId, @Param("statisticsTypes") List<String> statisticsTypes,
             @Param("statisticsSubType") String statisticsSubType,
             @Param("startDate") OffsetDateTime startDate, @Param("endDate") OffsetDateTime endDate,
             @Param("teamId") UUID teamId);

    @Query(name = "findStatisticsLanguagesByPeriodOutOfTeam", nativeQuery = true)
    List<StatisticsDashboardEntity> findStatisticsLanguagesByPeriodOutOfTeam
            (@Param("userId") UUID userId, @Param("statisticsTypes") List<String> statisticsTypes,
             @Param("statisticsSubType") String statisticsSubType,
             @Param("startDate") OffsetDateTime startDate, @Param("endDate") OffsetDateTime endDate);

    @Query(name = "findStatisticsKeysByPeriodAndTeam", nativeQuery = true)
    List<StatisticsDashboardEntity> findStatisticsKeysByPeriodAndTeam
            (@Param("userId") UUID userId, @Param("statisticsType") String statisticsType,
             @Param("statisticsSubType") String statisticsSubType,
             @Param("startDate") OffsetDateTime startDate, @Param("endDate") OffsetDateTime endDate,
             @Param("teamId") UUID teamId);

    @Query(name = "findStatisticsKeysByPeriodOutOfTeam", nativeQuery = true)
    List<StatisticsDashboardEntity> findStatisticsKeysByPeriodOutOfTeam
            (@Param("userId") UUID userId, @Param("statisticsType") String statisticsType,
             @Param("statisticsSubType") String statisticsSubType,
             @Param("startDate") OffsetDateTime startDate, @Param("endDate") OffsetDateTime endDate);

}
