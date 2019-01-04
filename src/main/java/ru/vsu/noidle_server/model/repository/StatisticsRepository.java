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

    StatisticsEntity getByTypeAndSubtypeAndExtravalueAndUserIdAndTeamId
            (StatisticsType statisticsType, StatisticsSubType statisticsSubType,
             String extraValue, UUID userId, UUID teamId);

    List<StatisticsEntity> getAllByUserIdAndSubtypeInAndDateGreaterThanEqualAndTeamId
            (UUID userId, StatisticsSubType[] statisticsSubType, OffsetDateTime date, UUID teamId);

    List<StatisticsEntity> getAllByUserIdAndTypeAndSubtypeAndTeamId
            (UUID userId, StatisticsType statisticsType, StatisticsSubType statisticsSubType, UUID teamId);

    List<StatisticsEntity> getAllByUserIdAndTypeInAndSubtypeAndDateGreaterThanEqualAndTeamId
            (UUID userId, StatisticsType[] statisticsType, StatisticsSubType statisticsSubtype, OffsetDateTime date,
             UUID teamId);

    List<StatisticsEntity> getAllByUserIdAndSubtypeInAndTeamId
            (UUID userId, StatisticsSubType[] statisticsSubType, UUID teamId);

    List<StatisticsEntity> getAllByUserIdAndTypeInAndSubtypeAndTeamId
            (UUID userId, StatisticsType[] statisticsType, StatisticsSubType statisticsSubType, UUID teamId);

    @Query(value = "SELECT s.type as type, s.subtype as subtype, s.extravalue as extravalue, sum(s.value) as value" +
            " FROM statistics s WHERE s.user_id = :userId and s.subtype in :statisticsSubTypes" +
            " and s.date >= :startDate  and s.date <= :endDate and s.team_id = :teamId" +
            "  group by s.type,s.subtype, s.extravalue",
            nativeQuery = true)
    List<StatisticsDashboardEntity> findStatisticsByPeriodAndTeam
            (@Param("userId") UUID userId, @Param("statisticsSubTypes") List<String> statisticsSubTypes,
             @Param("startDate") OffsetDateTime startDate, @Param("endDate") OffsetDateTime endDate,
             @Param("teamId") UUID teamId);
}
