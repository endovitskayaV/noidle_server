package ru.vsu.noidle_server.model;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.UUID;

@Entity
@Table(name = "team")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class TeamEntity {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "photo")
    private String photo;

    @ManyToMany(mappedBy = "teams") //refers to UserEntity  private Collection<TeamEntity> teams
    private Collection<UserEntity> users;

    @OneToMany(mappedBy = "team") //refers to ProjectEntity  private TeamEntity team
    private Collection<ProjectEntity> projects;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompanyEntity company;
}
