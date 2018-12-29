package ru.vsu.noidle_server.model.mapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import ru.vsu.noidle_server.model.StatisticsSubType;
import ru.vsu.noidle_server.model.StatisticsType;
import ru.vsu.noidle_server.model.domain.*;
import ru.vsu.noidle_server.model.dto.*;
import ru.vsu.noidle_server.utils.TimeUtils;

import java.util.*;

@Mapper(componentModel = "spring")
public interface DataMapper {

    @Mapping(source = "statisticsDto.id", target = "id")
    @Mapping(source = "userEntity", target = "user")
    StatisticsEntity toEntity(StatisticsDto statisticsDto, UserEntity userEntity, @Context CycleAvoidingMappingContext context);

    StatisticsDto toDto(StatisticsEntity statisticsEntity);

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

//    @SuppressWarnings(value = "unchecked") //aboutUser.getUserAuthentication().getDetails()) - Object
//    default UserDto toDto(OAuth2Authentication aboutUser) {
//        LinkedHashMap<String, String> details = ((LinkedHashMap<String, String>) aboutUser.getUserAuthentication().getDetails());
//        String photo = details.containsKey("avatar_url") ? details.get("avatar_url") : details.get("picture");
//
//        return new UserDto(details.get("email"), getName(details), photo, null);
//    }

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

    default Map<String, String> toDtosDashboard(List<StatisticsEntity> statistics) {
        if (statistics == null || statistics.isEmpty()) {
            return null;
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

    default Map.Entry<String, String> toDtoDashboard(StatisticsEntity statisticsEntity) {
        if (statisticsEntity == null || statisticsEntity.getValue() == null) {
            return null;
        }
        StringBuilder resultType = new StringBuilder();

        StatisticsType type = statisticsEntity.getType();
        resultType.append(type != null ? type.getShortcut() : "");

        StatisticsSubType subType = statisticsEntity.getSubType();
        resultType.append(subType != null ? subType.getShortcut() : "");

        String extraValue = statisticsEntity.getExtraValue();
        resultType.append(extraValue != null ? extraValue : "");
        String value = statisticsEntity.getValue().toString();
        HashMap<String, String> result = new HashMap<>(1);

        if (StatisticsType.TIME.equals(type)) {
            value = TimeUtils.toPretty(statisticsEntity.getValue());
        }
        result.put(resultType.toString(), value);
        return (Map.Entry<String, String>) result.entrySet().toArray()[0];
    }

    default Map<String, String> toDtosKeys(List<StatisticsEntity> statisticsEntities) {
        if (statisticsEntities == null || statisticsEntities.isEmpty()) {
            return Collections.emptyMap();
        }

        HashMap<String, String> result = new HashMap<>(statisticsEntities.size());
        statisticsEntities.forEach(statisticsEntity -> {
            if (statisticsEntity != null && statisticsEntity.getValue() != null &&
                    statisticsEntity.getExtraValue() != null &&
                    statisticsEntity.getValue() != null) {

                String keyName = statisticsEntity.getExtraValue();
                String value = statisticsEntity.getValue().toString();
                result.put(keyName, value);
            }
        });

        return result;
    }


    default Set<LanguageStatisticsDto> toDtosLanguages(List<StatisticsEntity> statisticsEntities) {
        if (statisticsEntities == null || statisticsEntities.isEmpty()) {
            return Collections.emptySet();
        }

        HashMap<String, TimeSymbols> langValues = new HashMap<>(statisticsEntities.size() / 2);
        statisticsEntities.forEach(statisticsEntity -> {
            if (statisticsEntity != null && statisticsEntity.getValue() != null &&
                    statisticsEntity.getExtraValue() != null &&
                    statisticsEntity.getValue() != null) {

                String language = statisticsEntity.getExtraValue();
                String value = statisticsEntity.getValue().toString();
                if (langValues.containsKey(language)) {
                    if (statisticsEntity.getType().equals(StatisticsType.LANG_SYMBOL)) {
                        langValues.get(language).setSymbols(value);
                    } else {
                        langValues.get(language).setTime(TimeUtils.toPretty(Long.parseLong(value)));
                    }
                } else {
                    langValues.put(
                            language,
                            statisticsEntity.getType().equals(StatisticsType.LANG_SYMBOL) ?
                                    new TimeSymbols("", value) :
                                    new TimeSymbols(TimeUtils.toPretty(Long.parseLong(value)), "")
                    );
                }
            }
        });

        HashSet<LanguageStatisticsDto> result = new HashSet<>(langValues.size());
        langValues.forEach((lang, timeSymbols) ->
                result.add(new LanguageStatisticsDto(lang, timeSymbols.symbols, timeSymbols.time)));
        return result;
    }

    @Setter
    @AllArgsConstructor
    class TimeSymbols {
        String time;
        String symbols;
    }
}
