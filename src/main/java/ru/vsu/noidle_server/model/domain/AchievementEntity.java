package ru.vsu.noidle_server.model.domain;

import lombok.*;
import org.jetbrains.annotations.NotNull;
import ru.vsu.noidle_server.model.AchievementType;
import ru.vsu.noidle_server.model.converter.AchievementTypeConverter;

import javax.persistence.*;

@Entity
@Table(name = "achievement")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class AchievementEntity implements Comparable<AchievementEntity> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Convert(converter = AchievementTypeConverter.class)
    @Column(name = "type", nullable = false)
    private AchievementType type;

    @Column(name = "level_number")
    private Long levelNumber;

    @Column(name = "name", nullable = false)
    private String name;

    public boolean isLevel() {
        return levelNumber != null;
    }

    @Override
    public int compareTo(@NotNull AchievementEntity o) {
        if (o.levelNumber == null && levelNumber == null) return 0;
        if (o.levelNumber == null) return -1;
        if (levelNumber == null) return 1;
        return levelNumber.compareTo(o.levelNumber);
    }
}