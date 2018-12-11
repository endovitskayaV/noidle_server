package ru.vsu.noidle_server.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.noidle_server.model.StatisticsSubType;
import ru.vsu.noidle_server.model.StatisticsType;
import ru.vsu.noidle_server.model.domain.StatisticsEntity;

import java.util.UUID;

@Repository
public interface StatisticsRepository extends JpaRepository<StatisticsEntity, UUID> {

    StatisticsEntity getByTypeAndSubTypeAndNameAndUserId(StatisticsType statisticsType, StatisticsSubType statisticsSubType, String name, UUID userId);
}
