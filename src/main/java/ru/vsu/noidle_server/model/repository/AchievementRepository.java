package ru.vsu.noidle_server.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.noidle_server.model.domain.AchievementEntity;

import java.util.List;
import org.jetbrains.annotations.NotNull;

@Repository
public interface AchievementRepository extends JpaRepository<AchievementEntity, Long> {

    AchievementEntity getByLevelNumber(@NotNull Long order);

    List<AchievementEntity> getAllByLevelNumberNull();
}
