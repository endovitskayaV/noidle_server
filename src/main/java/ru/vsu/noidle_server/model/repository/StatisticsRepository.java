package ru.vsu.noidle_server.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.noidle_server.model.StatisticsSubType;
import ru.vsu.noidle_server.model.StatisticsType;
import ru.vsu.noidle_server.model.domain.StatisticsEntity;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface StatisticsRepository extends JpaRepository<StatisticsEntity, UUID> {

    StatisticsEntity getByTypeAndSubTypeAndExtraValueAndUserIdAndTeamId(StatisticsType statisticsType, StatisticsSubType statisticsSubType, String extraValue, UUID userId, UUID teamId);

    List<StatisticsEntity> getAllByUserIdAndDateGreaterThanEqualAndTeamId(UUID userId, OffsetDateTime date, UUID teamId);

    List<StatisticsEntity> getAllByUserIdAndTypeAndSubTypeAndDateGreaterThanEqualAndTeamId
            (UUID userId, StatisticsType statisticsType, StatisticsSubType statisticsSubType, OffsetDateTime date, UUID teamId);

    List<StatisticsEntity> getAllByUserIdAndTypeInAndSubTypeAndDateGreaterThanEqualAndTeamId
            (UUID userId, StatisticsType[] statisticsType, StatisticsSubType statisticsSubType, OffsetDateTime date, UUID teamId);
}
