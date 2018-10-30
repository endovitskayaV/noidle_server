package ru.vsu.noidle_server.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "achievement_type")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "code")
public class AchievementTypeEntity {

    @Id
    @Enumerated (value = EnumType.ORDINAL)
    @Column(name = "code")
    private Achievement code;

    @Column(name = "name")
    private String name;
}
