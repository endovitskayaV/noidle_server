package ru.vsu.noidle_server.model.mapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Triple;
import org.jetbrains.annotations.NotNull;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.vsu.noidle_server.Constants;
import ru.vsu.noidle_server.model.StatisticsSubType;
import ru.vsu.noidle_server.model.StatisticsType;
import ru.vsu.noidle_server.model.UpdateRole;
import ru.vsu.noidle_server.model.domain.*;
import ru.vsu.noidle_server.model.dto.*;
import ru.vsu.noidle_server.utils.TimeUtils;

import java.util.*;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface DataMapper {

    default List<Triple<String, String, Float>> toDashboardRequirements(List<RequirementEntity> requirementEntities) {
        if (requirementEntities == null || requirementEntities.isEmpty()) {
            return Collections.emptyList();
        }
        List<Triple<String, String, Float>> dashboardRequirements = new ArrayList<>();
        requirementEntities.forEach(requirementEntity -> {
            Triple<String, String, Float> triple = toDashboardRequirement(requirementEntity);
            if (triple != null) {
                dashboardRequirements.add(triple);
            }
        });
        return dashboardRequirements;
    }

    default Triple<String, String, Float> toDashboardRequirement(RequirementEntity requirementEntity) {
        if (requirementEntity == null
                || requirementEntity.getStatisticsType() == null
                || requirementEntity.getStatisticsSubType() == null
                || requirementEntity.getValue() == null) {
            return null;
        }

        String name = null;
        if (StatisticsType.TIME.equals(requirementEntity.getStatisticsType())
                || StatisticsType.SYMBOL.equals(requirementEntity.getStatisticsType())) {

            name = addSubType(requirementEntity, requirementEntity.getStatisticsType().getDto());

        } else if (requirementEntity.getExtraValue() != null) {

            if (StatisticsType.COMMIT.equals(requirementEntity.getStatisticsType())
                    || StatisticsType.EXEC.equals(requirementEntity.getStatisticsType())
                    || StatisticsType.SINGLE_KEY.equals(requirementEntity.getStatisticsType())) {

                name = addSubType(requirementEntity, requirementEntity.getExtraValue() + " " + requirementEntity.getStatisticsType().getDto());

            } else {
                name = addSubType(requirementEntity, requirementEntity.getStatisticsType().getDto() + " " + requirementEntity.getExtraValue());
            }
        }

        String value = requirementEntity.getValue().toString();
        if (StatisticsType.TIME.equals(requirementEntity.getStatisticsType())
                || StatisticsType.LANG_TIME.equals(requirementEntity.getStatisticsType())) {
            value = TimeUtils.toPretty(requirementEntity.getValue());
        }

        Float teamContributionRate = null;
        if (requirementEntity.getTeamContributionRate() != null) {
            teamContributionRate = requirementEntity.getTeamContributionRate();
        }
        return name == null ? null : new ImmutableTriple<>(name, value, teamContributionRate);
    }

    default String addSubType(RequirementEntity requirementEntity, String name) {
        if (StatisticsSubType.PER_DAY.equals(requirementEntity.getStatisticsSubType())) {

            name += " " + requirementEntity.getStatisticsSubType().getDto();

        } else if (StatisticsSubType.PER_LIFE.equals(requirementEntity.getStatisticsSubType())
                || StatisticsSubType.CONTINUOUS_PER_LIFE.equals(requirementEntity.getStatisticsSubType())) {

            name = requirementEntity.getStatisticsSubType().getDto() + " " + name;

        } else {
            StringBuilder nameBuilder = new StringBuilder(requirementEntity.getStatisticsSubType().getDto());
            nameBuilder.insert(14, " " + name + " ");
            name = nameBuilder.toString();
        }

        return name;
    }

    default String toString(UserEntity userEntity) {
        if (userEntity == null) {
            return "";
        }
        String result = "";
        if (userEntity.getEmail() != null) {
            result += "email=" + userEntity.getEmail();
        }
        if (userEntity.getName() != null) {
            result += " name=" + userEntity.getName();
        }
        return result;
    }

    default List<UserEntity> toEntity(String usersData) {
        List<UserEntity> userEntities = new ArrayList<>();
        for (String userData : usersData.split(Constants.USERS_SEPARATOR)) {
            try {
                int index = userData.indexOf(Constants.USER_DATA_SEPARATOR);
                String name = userData.substring(0, index);
                userData = userData.substring(index + Constants.USER_DATA_SEPARATOR.length());

                index = userData.indexOf(Constants.USER_DATA_SEPARATOR);
                String email = userData.substring(0, index);
                userData = userData.substring(index + Constants.USER_DATA_SEPARATOR.length());

                String password;
                String photo = null;
                index = userData.indexOf(Constants.USER_DATA_SEPARATOR);
                if (index == -1) {
                    password = userData;
                } else {
                    password = userData.substring(0, index);
                    if (index < userData.length()) {
                        userData = userData.substring(index + Constants.USER_DATA_SEPARATOR.length());
                        photo = userData;
                    }
                }

                userEntities.add(new UserEntity(email, name, password, photo, UpdateRole.USER));
            } catch (IndexOutOfBoundsException e) {
                return null;
            }
        }
        return userEntities;
    }

    @Mapping(source = "statisticsDto.id", target = "id")
    @Mapping(source = "userEntity", target = "user")
    @Mapping(source = "teamEntity", target = "team")
    StatisticsEntity toEntity(StatisticsDto statisticsDto, UserEntity userEntity, TeamEntity teamEntity, @Context CycleAvoidingMappingContext context);


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

    StatisticsDto toDto(StatisticsEntity statisticsEntity);

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
