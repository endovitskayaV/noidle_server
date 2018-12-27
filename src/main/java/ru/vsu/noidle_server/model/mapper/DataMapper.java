package ru.vsu.noidle_server.model.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import ru.vsu.noidle_server.model.StatisticsSubType;
import ru.vsu.noidle_server.model.StatisticsType;
import ru.vsu.noidle_server.model.domain.*;
import ru.vsu.noidle_server.model.dto.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    default Map<String, Long> toDtosDashboard(List<StatisticsEntity> statistics) {
        if (statistics == null || statistics.isEmpty()) {
            return null;
        }
        Map<String, Long> dashboard = new HashMap<>(statistics.size());
        statistics.forEach(statisticsEntity -> {
            Map.Entry<String, Long> entry = toDtoDashboard(statisticsEntity);
            if (entry != null) {
                dashboard.put(entry.getKey(), entry.getValue());
            }
        });

        return dashboard;
    }

    default Map.Entry<String, Long> toDtoDashboard(StatisticsEntity statisticsEntity) {
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
        HashMap<String, Long> result = new HashMap<>(1);
        result.put(resultType.toString(), statisticsEntity.getValue());
        return (Map.Entry<String, Long>) result.entrySet().toArray()[0];
    }
}
