package ru.vsu.noidle_server.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.noidle_server.model.SubType;
import ru.vsu.noidle_server.model.Type;
import ru.vsu.noidle_server.model.domain.StatisticsEntity;

import java.util.List;
import java.util.UUID;

@Repository
public interface StatisticsRepository extends JpaRepository<StatisticsEntity, UUID> {

    StatisticsEntity getByTypeAndSubTypeAndNameAndUserId(Type type, SubType subType, String name, UUID userId);
}
