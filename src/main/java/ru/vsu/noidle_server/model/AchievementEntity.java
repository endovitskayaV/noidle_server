package ru.vsu.noidle_server.model;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "achievement")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class AchievementEntity {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "value")
    private Long value;

    @Column(name = "photo")
    private String photo;

    @ManyToOne
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "type")
    private AchievementTypeEntity achievementType;
}
