package ru.vsu.noidle_server.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.noidle_server.model.domain.RequirementEntity;

import java.util.List;

@Repository
public interface RequirementRepository extends JpaRepository<RequirementEntity, Long> {

    List<RequirementEntity> getAllByLevelOrder(Long levelOrder);
}
