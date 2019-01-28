package ru.vsu.noidle_server.model.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.jetbrains.annotations.NotNull;
import ru.vsu.noidle_server.model.UserRole;
import ru.vsu.noidle_server.model.converter.UserRoleConverter;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "team")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(exclude = "usersTeams")
public class TeamEntity implements Comparable<TeamEntity> {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "photo")
    private String photo;

    @Column(name = "created")
    private OffsetDateTime created;

    @OneToMany(mappedBy = "team", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<UserTeam> usersTeams;

    public TeamEntity(String name, OffsetDateTime created) {
        this.name = name;
        this.created = created;
    }

    @Override
    public int compareTo(@NotNull TeamEntity o) {
        if (o.getCreated() == null && created == null) {
            return 0;
        }
        if (o.getCreated() == null && created != null) {
            return 1;
        }
        if (o.getCreated() != null && created == null) {
            return -1;
        } else {
            return created.compareTo(o.getCreated());
        }
    }

    public Set<UserEntity> getUsers() {
        return usersTeams.stream().map(UserTeam::getUser).collect(Collectors.toSet());
    }

    public void removeTeamMember(UserEntity userEntity) {
        usersTeams.remove(new UserTeam(this, userEntity, null));
    }

    public void addTeamMember(UserEntity userEntity, UserRole userRole) {
        usersTeams.add(new UserTeam(this, userEntity, userRole));
    }
}