package ru.vsu.noidle_server.model.converter;

import ru.vsu.noidle_server.model.AchievementType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class AchievementTypeConverter implements AttributeConverter<AchievementType, String> {

    @Override
    public String convertToDatabaseColumn(AchievementType achievementType) {
        return achievementType == null ? null : achievementType.getShortcut();
    }

    @Override
    public AchievementType convertToEntityAttribute(String shortcut) {
        return AchievementType.byShortcut(shortcut);
    }
}
