package ru.vsu.noidle_server.model.converter;

import ru.vsu.noidle_server.model.SubType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class SubTypeConverter implements AttributeConverter<SubType, String> {

    @Override
    public String convertToDatabaseColumn(SubType subType) {
        return subType == null ? null : subType.getShortcut();
    }

    @Override
    public SubType convertToEntityAttribute(String shortcut) {
        return SubType.byShortcut(shortcut);
    }
}
