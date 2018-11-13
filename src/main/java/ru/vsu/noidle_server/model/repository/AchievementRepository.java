package ru.vsu.noidle_server.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vsu.noidle_server.model.SubType;
import ru.vsu.noidle_server.model.Type;
import ru.vsu.noidle_server.model.domain.AchievementEntity;

import java.util.UUID;

public interface AchievementRepository extends JpaRepository<AchievementEntity, UUID> {

    AchievementEntity getByTypeAndSubTypeAndNameAndUserId(Type type, SubType subType, String name, UUID userId);
}
