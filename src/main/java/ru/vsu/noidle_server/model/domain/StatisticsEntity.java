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

@NamedNativeQuery(
        name = "findStatistics",
        query = "SELECT s.extra_value as extravalue , s.type as type, s.sub_type as subtype, sum(s.value) as sum FROM statistics s WHERE s.user_id = :userId and s.sub_type in (:statisticsSubType1, :statisticsSubType2)" +
                " and s.date >= :startDate  and s.date <= :endDate and s.team_id= :teamId  group by s.type,s.sub_type, s.extra_value,s.user_id, s.team_id"
        , resultSetMapping = "employee-details"
)
@SqlResultSetMapping(name = "employee-details",
        classes = {
                @ConstructorResult(targetClass = StatisticsSumEntity.class, columns = {
                        @ColumnResult(name = "extravalue", type = String.class),
                        @ColumnResult(name = "type", type = String.class),
                        @ColumnResult(name = "subtype", type = String.class),
                        @ColumnResult(name = "sum", type = Long.class)
                })
        }
)
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
    @Column(name = "subType", nullable = false)
    private StatisticsSubType subType;

    @Column(name = "extra_value")
    private String extraValue;

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