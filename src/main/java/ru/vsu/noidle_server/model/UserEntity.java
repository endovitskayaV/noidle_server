package ru.vsu.noidle_server.model;

import lombok.*;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "user_data")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class UserEntity {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "photo")
    private String photo;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_data_team",
            joinColumns = @JoinColumn(name="user_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "team_id", referencedColumnName = "id")
    )
    private Collection<TeamEntity> teams;

    @OneToMany(mappedBy = "user")
    private Collection<AchievementEntity> achievements;

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "user_data_role",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "role",referencedColumnName = "id")
//    )
//    @MapKeyJoinColumn(name = "project_id")

    @ElementCollection
    @CollectionTable(name="user_data_role", joinColumns=@JoinColumn(name="user_id", referencedColumnName = "id"))
    @MapKeyJoinColumn (name="project_id")
   // @Column
    @JoinColumn(name = "role_code",referencedColumnName = "id")
    @AttributeOverrides(
            @AttributeOverride(name = "code", column = @Column(name = "role_code"))
    )
    private Map<ProjectEntity, RoleEntity>  rolesByProjects;
}
