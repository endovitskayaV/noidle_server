package ru.vsu.noidle_server.model.domain;

import lombok.*;
import ru.vsu.noidle_server.model.StatisticsSubType;
import ru.vsu.noidle_server.model.StatisticsType;
import ru.vsu.noidle_server.model.converter.StatisticsSubTypeConverter;
import ru.vsu.noidle_server.model.converter.StatisticsTypeConverter;

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

    @Convert(converter = StatisticsTypeConverter.class)
    @Column(name = "type", nullable = false)
    private StatisticsType statisticsType;

    @Convert(converter = StatisticsSubTypeConverter.class)
    @Column(name = "subType", nullable = false)
    private StatisticsSubType statisticsSubType;

    @ManyToOne
    @JoinColumn(name = "achievement_id", referencedColumnName = "id")
    private AchievementEntity achievement;

    @Column(name = "extra_value")
    private String extraValue;

    @Column(name = "value", nullable = false)
    private Long value;

    @Column(name = "team_contribution_rate")
    private Float teamContributionRate;

    public boolean anyFits(Collection<StatisticsEntity> statistics) {
        if (statistics == null || statistics.isEmpty()) {
            return false;
        }
        boolean anyFits = statistics.stream().anyMatch(statistic ->
                statisticsType.equals(statistic.getType()) &&
                        statisticsSubType.equals(statistic.getSubType()) &&
                        compareExtraValue(statistic.getExtraValue()) &&
                        value.compareTo(statistic.getValue()) <= 0);// value <=  achievement.getValue()
        return anyFits;
    }

    private boolean compareExtraValue(String anotherValue) {
        return (extraValue == null && (anotherValue == null || anotherValue.isEmpty())) ||
                (extraValue != null && extraValue.isEmpty() && (anotherValue == null || anotherValue.isEmpty())) ||
                Objects.equals(extraValue, anotherValue);
    }
}
