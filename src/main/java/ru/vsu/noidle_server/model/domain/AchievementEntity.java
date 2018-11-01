package ru.vsu.noidle_server.model.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import ru.vsu.noidle_server.model.Achievement;
import ru.vsu.noidle_server.model.converter.AchievementConverter;

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
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", nullable = false)
    private UUID id;

    @Convert(converter = AchievementConverter.class)
    @Column(name = "type")
    private Achievement type;

    @Column(name = "name")
    private String name;

    @Column(name = "value")
    private Long value;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    @Override
    public String toString() {
        return "AchievementEntity{" +
                "id=" + id +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", value=" + value +
                ", userId=" + user.getId() +
                '}';
    }
}