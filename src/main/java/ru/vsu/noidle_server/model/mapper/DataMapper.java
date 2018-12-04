package ru.vsu.noidle_server.model.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import ru.vsu.noidle_server.model.domain.AchievementEntity;
import ru.vsu.noidle_server.model.domain.RequirementEntity;
import ru.vsu.noidle_server.model.domain.StatisticsEntity;
import ru.vsu.noidle_server.model.domain.UserEntity;
import ru.vsu.noidle_server.model.dto.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface DataMapper {

    @Mapping(source = "statisticsDto.id", target = "id")
    @Mapping(source = "statisticsDto.name", target = "name")
    @Mapping(source = "userEntity", target = "user")
    StatisticsEntity toEntity(StatisticsDto statisticsDto, UserEntity userEntity, @Context CycleAvoidingMappingContext context);

    @SuppressWarnings(value = "unchecked") //aboutUser.getUserAuthentication().getDetails()) - Object
    default UserEntity toEntity(OAuth2Authentication user) {
        LinkedHashMap<String, String> details = ((LinkedHashMap<String, String>) user.getUserAuthentication().getDetails());
        String photo = details.containsKey("avatar_url") ? details.get("avatar_url") : details.get("picture");

        //TODO: check if assigning null to map annuls relationships
        return new UserEntity(null, getEmail(user), getName(details), photo, null, null, null);
    }

//    @SuppressWarnings(value = "unchecked") //aboutUser.getUserAuthentication().getDetails()) - Object
//    default UserDto toDto(OAuth2Authentication aboutUser) {
//        LinkedHashMap<String, String> details = ((LinkedHashMap<String, String>) aboutUser.getUserAuthentication().getDetails());
//        String photo = details.containsKey("avatar_url") ? details.get("avatar_url") : details.get("picture");
//
//        return new UserDto(details.get("email"), getName(details), photo, null);
//    }

    @SuppressWarnings(value = "unchecked") //aboutUser.getUserAuthentication().getDetails()) - Object
    static String getEmail(OAuth2Authentication user) {
        LinkedHashMap<String, String> details = ((LinkedHashMap<String, String>) user.getUserAuthentication().getDetails());
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
}
