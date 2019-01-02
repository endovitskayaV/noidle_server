package ru.vsu.noidle_server.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.vsu.noidle_server.model.StatisticsSubType;
import ru.vsu.noidle_server.model.StatisticsType;
import ru.vsu.noidle_server.model.domain.StatisticsEntity;
import ru.vsu.noidle_server.model.domain.StatisticsSumEntity;

import javax.persistence.NamedQuery;
import java.time.OffsetDateTime;
import java.util.Collection;
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

//    @Query(value = "SELECT s.extra_value as extravalue , s.type as type, s.sub_type as subtype FROM statistics s WHERE s.user_id = :userId and s.sub_type in :statisticsSubType" +
//            " and s.date >= :startDate  and s.date <= :endDate and s.team_id= :teamId",// +
//            //" group by s.type,s.sub_type, s.extra_value,s.user_id, s.team_id",
//            nativeQuery = true)
    @Query(name = "findStatistics", nativeQuery = true)
    Collection<StatisticsSumEntity> findStatistics
            (@Param("userId") UUID userId, @Param("statisticsSubType1") String statisticsSubType,
             @Param("statisticsSubType2") String statisticsSubType2,

             @Param("startDate") OffsetDateTime startDate, @Param("endDate") OffsetDateTime endDate,
             @Param("teamId") UUID teamId);

}
