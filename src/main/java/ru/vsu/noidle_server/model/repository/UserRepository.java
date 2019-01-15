package ru.vsu.noidle_server.model.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.noidle_server.model.UpdateRole;
import ru.vsu.noidle_server.model.domain.UserEntity;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    UserEntity findByEmail(String email);

    UserEntity findByName(String name);

    List<UserEntity> findAllByUpdateRole(UpdateRole updateRole);

    UserEntity findByEmailOrNameOrId(String email, String name, UUID id);
}
