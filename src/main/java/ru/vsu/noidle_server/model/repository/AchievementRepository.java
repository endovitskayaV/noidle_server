package ru.vsu.noidle_server.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vsu.noidle_server.model.domain.AchievementEntity;

import java.util.UUID;

public interface AchievementRepository extends JpaRepository<AchievementEntity, UUID> {
}
