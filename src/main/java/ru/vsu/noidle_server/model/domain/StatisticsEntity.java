package ru.vsu.noidle_server.model.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import ru.vsu.noidle_server.model.SubType;
import ru.vsu.noidle_server.model.Type;
import ru.vsu.noidle_server.model.converter.SubTypeConverter;
import ru.vsu.noidle_server.model.converter.TypeConverter;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "statistics")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class StatisticsEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", nullable = false)
    private UUID id;

    @Convert(converter = TypeConverter.class)
    @Column(name = "type", nullable = false)
    private Type type;

    @Convert(converter = SubTypeConverter.class)
    @Column(name = "subType", nullable = false)
    private SubType subType;

    @Column(name = "name")
    private String name;

    @Column(name = "value", nullable = false)
    private Long value;

    @Column(name = "date", nullable = false)
    private OffsetDateTime date;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    @Override
    public String toString() {
        return "StatisticsEntity{" +
                "id=" + id +
                ", type=" + type +
                ", subType=" + subType +
                ", name='" + name + '\'' +
                ", value=" + value +
                ", date=" + date +
                ", userId=" + user.getId() +
                '}';
    }
}