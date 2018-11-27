package ru.vsu.noidle_server.model.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import ru.vsu.noidle_server.model.domain.AchievementEntity;
import ru.vsu.noidle_server.model.domain.UserEntity;
import ru.vsu.noidle_server.model.dto.AchievementDto;
import ru.vsu.noidle_server.model.dto.UserDto;

import java.util.LinkedHashMap;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "achievementDto.userId", target = "user", qualifiedByName = "mapUserId")
    AchievementEntity toEntity(AchievementDto achievementDto, @Context CycleAvoidingMappingContext context);

    @Named("mapUserId")
    default UserEntity mapUserId(UUID userId) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        return userEntity;
    }

    @Mapping(source = "achievementEntity.user.id", target = "userId")
    AchievementDto toDto(AchievementEntity achievementEntity, @Context CycleAvoidingMappingContext context);


    @SuppressWarnings(value = "unchecked") //user.getUserAuthentication().getDetails()) - Object
    default UserEntity toEntity(OAuth2Authentication user) {
        LinkedHashMap<String, String> details = ((LinkedHashMap<String, String>) user.getUserAuthentication().getDetails());
        String photo = details.containsKey("avatar_url") ? details.get("avatar_url") : details.get("picture");

        //TODO: check if assigning null to map annuls relationships
        return new UserEntity(null, getEmail(user), getName(details), photo, null, null, null);
    }

//    @SuppressWarnings(value = "unchecked") //user.getUserAuthentication().getDetails()) - Object
//    default UserDto toDto(OAuth2Authentication user) {
//        LinkedHashMap<String, String> details = ((LinkedHashMap<String, String>) user.getUserAuthentication().getDetails());
//        String photo = details.containsKey("avatar_url") ? details.get("avatar_url") : details.get("picture");
//
//        return new UserDto(details.get("email"), getName(details), photo, null);
//    }

    @SuppressWarnings(value = "unchecked") //user.getUserAuthentication().getDetails()) - Object
    static String getEmail(OAuth2Authentication user){
        LinkedHashMap<String, String> details = ((LinkedHashMap<String, String>) user.getUserAuthentication().getDetails());
        return  details.get("email").toLowerCase();
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
}
