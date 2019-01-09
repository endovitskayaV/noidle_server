package ru.vsu.noidle_server.model.mapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.mapstruct.*;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import ru.vsu.noidle_server.model.StatisticsType;
import ru.vsu.noidle_server.model.domain.*;
import ru.vsu.noidle_server.model.dto.*;
import ru.vsu.noidle_server.utils.TimeUtils;

import java.util.*;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface DataMapper {

    @Mapping(source = "statisticsDto.id", target = "id")
    @Mapping(source = "userEntity", target = "user")
    @Mapping(source = "teamEntity", target = "team")
    StatisticsEntity toEntity(StatisticsDto statisticsDto, UserEntity userEntity, TeamEntity teamEntity, @Context CycleAvoidingMappingContext context);

    @SuppressWarnings(value = "unchecked") //aboutUser.getUserAuthentication().getDetails()) - Object
    default UserEntity toEntity(OAuth2Authentication user) {
        if (user == null) {
            return null;
        }
        LinkedHashMap<String, String> details = ((LinkedHashMap<String, String>) user.getUserAuthentication().getDetails());
        String photo = details.containsKey("avatar_url") ? details.get("avatar_url") : details.get("picture");

        //TODO: check if assigning null to map annuls relationships
        return new UserEntity(getEmail(user), getName(details), photo);
    }

    @SuppressWarnings(value = "unchecked") //aboutUser.getUserAuthentication().getDetails()) - Object
    default String getEmail(OAuth2Authentication user) {
        if (user == null) {
            return null;
        }
        LinkedHashMap<String, String> details = ((LinkedHashMap<String, String>) user.getUserAuthentication().getDetails());
        return details.get("email").toLowerCase();
    }

    default String getEmail(LinkedHashMap<String, String> details) {
        if (details == null || details.isEmpty()) {
            return null;
        }
        return details.get("email").toLowerCase();
    }

    default String getName(LinkedHashMap<String, String> details) {
        String name;
        if (details.containsKey("login")) {
            name = details.get("login");
        } else if (details.containsKey("username")) {
            name = details.get("username");
        } else {
            name = details.get("name");
        }
        return name;
    }

    UserDto toDto(UserEntity userEntity, @Context CycleAvoidingMappingContext context);

    UserDtoForNotification toDtoForNotification(UserEntity userEntity);

    AchievementDto toDto(AchievementEntity achievementEntity);

    List<RequirementDto> toDto(List<RequirementEntity> requirementEntity);

    TeamEntity toEntity(TeamDto teamDto, @Context CycleAvoidingMappingContext context);

    TeamDto toDto(TeamEntity teamEntity, @Context CycleAvoidingMappingContext context);

    TeamDtoShort toDtoShort(TeamEntity teamEntity);

    List<StatisticsDashboardEntity> toDashboardEntities(List<StatisticsEntity> statisticsEntity);

    @Mapping(source = "statisticsEntity", target = "type", qualifiedByName = "getTypeShortcut")
    @Mapping(source = "statisticsEntity", target = "subType", qualifiedByName = "getSubTypeShortcut")
    StatisticsDashboardEntity toDashboardEntity(StatisticsEntity statisticsEntity);

    @Named("getTypeShortcut")
    default String getTypeShortcut(StatisticsEntity statisticsEntity) {
        return statisticsEntity.getType().getShortcut();
    }

    @Named("getSubTypeShortcut")
    default String getSubTypeShortcut(StatisticsEntity statisticsEntity) {
        return statisticsEntity.getSubType().getShortcut();
    }

    default Map<String, String> toDtosDashboard(List<StatisticsDashboardEntity> statistics) {
        if (statistics == null || statistics.isEmpty()) {
            return Collections.emptyMap();
        }
        Map<String, String> dashboard = new HashMap<>(statistics.size());
        statistics.forEach(statisticsEntity -> {
            Map.Entry<String, String> entry = toDtoDashboard(statisticsEntity);
            if (entry != null) {
                dashboard.put(entry.getKey(), entry.getValue());
            }
        });

        return dashboard;
    }

    default Map.Entry<String, String> toDtoDashboard(StatisticsDashboardEntity statisticsDashboardEntity) {
        if (statisticsDashboardEntity == null || statisticsDashboardEntity.getValue() == null) {
            return null;
        }
        StringBuilder resultType = new StringBuilder();

        resultType.append(statisticsDashboardEntity.getType() != null ? statisticsDashboardEntity.getType() : "");
        resultType.append(statisticsDashboardEntity.getSubType() != null ? statisticsDashboardEntity.getSubType() : "");

        String extraValue = statisticsDashboardEntity.getExtraValue();
        resultType.append(extraValue != null ? extraValue : "");
        String value = statisticsDashboardEntity.getValue().toString();
        HashMap<String, String> result = new HashMap<>(1);

        if (StatisticsType.TIME.getShortcut().equals(statisticsDashboardEntity.getType())) {
            value = TimeUtils.toPretty(statisticsDashboardEntity.getValue());
        }
        result.put(resultType.toString(), value);
        return (Map.Entry<String, String>) result.entrySet().toArray()[0];
    }

    default Map<String, Long> toDtosKeys(List<StatisticsDashboardEntity> statisticsDashboardEntities) {
        if (statisticsDashboardEntities == null || statisticsDashboardEntities.isEmpty()) {
            return Collections.emptyMap();
        }

        HashMap<String, Long> result = new HashMap<>(statisticsDashboardEntities.size());
        statisticsDashboardEntities.forEach(statisticsDashboardEntity -> {
            if (statisticsDashboardEntity != null && statisticsDashboardEntity.getValue() != null &&
                    statisticsDashboardEntity.getExtraValue() != null &&
                    statisticsDashboardEntity.getValue() != null) {

                String keyName = statisticsDashboardEntity.getExtraValue();
                Long value = statisticsDashboardEntity.getValue();
                result.put(keyName, value);
            }
        });
        LinkedHashMap<String, Long> sorted = result.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> v2, LinkedHashMap::new));

        return sorted;
    }


    default SortedSet<LanguageStatisticsDto> toDtosLanguages(List<StatisticsDashboardEntity> statisticsDashboardEntities) {
        if (statisticsDashboardEntities == null || statisticsDashboardEntities.isEmpty()) {
            return Collections.emptySortedSet();
        }

        HashMap<String, TimeSymbols> langValues = new HashMap<>(statisticsDashboardEntities.size() / 2);
        statisticsDashboardEntities.forEach(statisticsDashboardEntity -> {
            if (statisticsDashboardEntity != null && statisticsDashboardEntity.getValue() != null &&
                    statisticsDashboardEntity.getExtraValue() != null &&
                    statisticsDashboardEntity.getValue() != null) {

                String language = statisticsDashboardEntity.getExtraValue();
                Long value = statisticsDashboardEntity.getValue();
                if (langValues.containsKey(language)) {
                    if (statisticsDashboardEntity.getType().equals(StatisticsType.LANG_SYMBOL.getShortcut())) {
                        langValues.get(language).setSymbols(value);
                    } else {
                        langValues.get(language).setTime(value);
                    }
                } else {
                    langValues.put(
                            language,
                            statisticsDashboardEntity.getType().equals(StatisticsType.LANG_SYMBOL.getShortcut()) ?
                                    new TimeSymbols(0L, value) :
                                    new TimeSymbols(value, 0L)
                    );
                }
            }
        });

        TreeSet<LanguageStatisticsDto> result = new TreeSet<>();
        LinkedHashMap<String, TimeSymbols> sorted = langValues.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> v2, LinkedHashMap::new));
        sorted.forEach((lang, timeSymbols) ->
                result.add(new LanguageStatisticsDto(
                        lang, timeSymbols.symbols.toString(), TimeUtils.toPretty(timeSymbols.time))
                ));
        return result;
    }

    @Setter
    @Getter
    @AllArgsConstructor
    class TimeSymbols implements Comparable<TimeSymbols> {
        Long time;
        Long symbols;

        @Override
        public int compareTo(@NotNull DataMapper.TimeSymbols o) {
            return this.symbols.compareTo(o.getSymbols());
        }
    }
}
