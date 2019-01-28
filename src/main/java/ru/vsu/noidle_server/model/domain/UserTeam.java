package ru.vsu.noidle_server.model.domain;

import lombok.*;
import ru.vsu.noidle_server.model.UserRole;
import ru.vsu.noidle_server.model.converter.UserRoleConverter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_data_team")
@IdClass(UserTeamId.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"team", "user"})
@ToString
public class UserTeam implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    private TeamEntity team;

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    @Convert(converter = UserRoleConverter.class)
    @Column(name = "user_role", nullable = false)
    private UserRole userRole;
}
