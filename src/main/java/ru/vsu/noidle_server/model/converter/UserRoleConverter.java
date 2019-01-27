package ru.vsu.noidle_server.model.converter;

import ru.vsu.noidle_server.model.UserRole;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class UserRoleConverter implements AttributeConverter<UserRole, String> {

    @Override
    public String convertToDatabaseColumn(UserRole userRole) {
        return userRole == null ? null : userRole.getShortcut();
    }

    @Override
    public UserRole convertToEntityAttribute(String shortcut) {
        return UserRole.byShortcut(shortcut);
    }
}
