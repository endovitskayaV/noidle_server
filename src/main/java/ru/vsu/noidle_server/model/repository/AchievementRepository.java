package ru.vsu.noidle_server.model.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.noidle_server.model.AchievementType;
import ru.vsu.noidle_server.model.domain.AchievementEntity;

import java.util.List;

@Repository
public interface AchievementRepository extends JpaRepository<AchievementEntity, Long> {

    AchievementEntity getByLevelNumber(@NotNull Long order);

    List<AchievementEntity> getAllByType(AchievementType achievementType);
}
