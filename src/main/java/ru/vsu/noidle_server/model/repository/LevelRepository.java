package ru.vsu.noidle_server.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.noidle_server.model.domain.LevelEntity;

@Repository
public interface LevelRepository extends JpaRepository<LevelEntity, Long> {

    LevelEntity getByOrder(Long order);
}
