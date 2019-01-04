package ru.vsu.noidle_server.model.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.jetbrains.annotations.NotNull;
import ru.vsu.noidle_server.model.StatisticsSubType;
import ru.vsu.noidle_server.model.StatisticsType;
import ru.vsu.noidle_server.model.converter.StatisticsSubTypeConverter;
import ru.vsu.noidle_server.model.converter.StatisticsTypeConverter;

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
@ToString
public class StatisticsEntity implements Comparable<StatisticsEntity> {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", nullable = false)
    private UUID id;

    @Convert(converter = StatisticsTypeConverter.class)
    @Column(name = "type", nullable = false)
    private StatisticsType type;

    @Convert(converter = StatisticsSubTypeConverter.class)
    @Column(name = "subtype", nullable = false)
    private StatisticsSubType subtype;

    @Column(name = "extravalue")
    private String extravalue;

    @Column(name = "value", nullable = false)
    private Long value;

    @Column(name = "date", nullable = false)
    private OffsetDateTime date;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    private TeamEntity team;

    @Override
    public int compareTo(@NotNull StatisticsEntity o) {
        return this.getValue().compareTo(o.getValue());
    }
}