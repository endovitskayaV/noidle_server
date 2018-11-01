package ru.vsu.noidle_server.model.mapper;

import org.mapstruct.Mapper;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import ru.vsu.noidle_server.model.domain.UserEntity;
import ru.vsu.noidle_server.model.dto.UserDto;

import java.util.LinkedHashMap;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @SuppressWarnings(value = "unchecked")
    default UserEntity convert(OAuth2Authentication user) {
        LinkedHashMap<String, String> details = ((LinkedHashMap<String, String>) user.getUserAuthentication().getDetails());
        return new UserEntity(null, details.get("email"), details.get("login"), details.get("avatar_url"));
    }

    UserDto convert(UserEntity userEntity);
}
