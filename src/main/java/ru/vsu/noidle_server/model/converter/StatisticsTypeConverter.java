package ru.vsu.noidle_server.model.converter;

import ru.vsu.noidle_server.model.StatisticsType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class StatisticsTypeConverter implements AttributeConverter<StatisticsType, String> {

    @Override
    public String convertToDatabaseColumn(StatisticsType statisticsType) {
        return statisticsType == null ? null : statisticsType.getShortcut();
    }

    @Override
    public StatisticsType convertToEntityAttribute(String shortcut) {
        return StatisticsType.byShortcut(shortcut);
    }
}
