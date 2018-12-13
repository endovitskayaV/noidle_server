package ru.vsu.noidle_server.model.converter;

import ru.vsu.noidle_server.model.StatisticsSubType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class StatisticsSubTypeConverter implements AttributeConverter<StatisticsSubType, String> {

    @Override
    public String convertToDatabaseColumn(StatisticsSubType statisticsSubType) {
        return statisticsSubType == null ? null : statisticsSubType.getShortcut();
    }

    @Override
    public StatisticsSubType convertToEntityAttribute(String shortcut) {
        return StatisticsSubType.byShortcut(shortcut);
    }
}
