package ru.vsu.noidle_server.model.converter;

import ru.vsu.noidle_server.model.Type;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class TypeConverter implements AttributeConverter<Type, String> {

    @Override
    public String convertToDatabaseColumn(Type type) {
        return type == null ? null : type.getShortcut();
    }

    @Override
    public Type convertToEntityAttribute(String shortcut) {
        return Type.byShortcut(shortcut);
    }
}
