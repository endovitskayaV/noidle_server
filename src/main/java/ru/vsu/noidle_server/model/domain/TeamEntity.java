package ru.vsu.noidle_server.model.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.UUID;

@Entity
@Table(name = "team")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(exclude = "users")
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

    @ManyToMany(mappedBy = "teams")
    //refers to UserEntity:  private Collection<TeamEntity> teams
    private Collection<UserEntity> users;

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
}