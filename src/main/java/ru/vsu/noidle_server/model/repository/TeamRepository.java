package ru.vsu.noidle_server.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vsu.noidle_server.model.domain.TeamEntity;

import java.util.UUID;

public interface TeamRepository extends JpaRepository<TeamEntity, UUID> {

    TeamEntity getByName(String name);
}
