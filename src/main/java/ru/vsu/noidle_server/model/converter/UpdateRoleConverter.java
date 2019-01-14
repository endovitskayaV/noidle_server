package ru.vsu.noidle_server.model.converter;

import ru.vsu.noidle_server.model.UpdateRole;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class UpdateRoleConverter implements AttributeConverter<UpdateRole, String> {

    @Override
    public String convertToDatabaseColumn(UpdateRole updateRole) {
        return updateRole == null ? null : updateRole.getShortcut();
    }

    @Override
    public UpdateRole convertToEntityAttribute(String shortcut) {
        return UpdateRole.byShortcut(shortcut);
    }
}
