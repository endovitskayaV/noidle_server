package ru.vsu.noidle_server.model.domain;

import lombok.*;
import ru.vsu.noidle_server.model.SubType;
import ru.vsu.noidle_server.model.Type;
import ru.vsu.noidle_server.model.converter.SubTypeConverter;
import ru.vsu.noidle_server.model.converter.TypeConverter;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "requirement")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class RequirementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Convert(converter = TypeConverter.class)
    @Column(name = "type", nullable = false)
    private Type type;

    @Convert(converter = SubTypeConverter.class)
    @Column(name = "subType", nullable = false)
    private SubType subType;

    @ManyToOne
    @JoinColumn(name = "level_order", nullable = false)
    private LevelEntity level;

    @Column(name = "name")
    private String name;

    @Column(name = "value", nullable = false)
    private Long value;

    public boolean anyFits(Collection<AchievementEntity> achievements) {
        if (achievements == null || achievements.isEmpty()) {
            return false;
        }
       boolean anyFits= achievements.stream().anyMatch(achievement ->
                        type.equals(achievement.getType()) &&
                        subType.equals(achievement.getSubType()) &&
                        Objects.equals(name, achievement.getName()) &&
                        value.compareTo(achievement.getValue()) <= -1); //value <  achievement.getValue()
        return anyFits;
    }
}
