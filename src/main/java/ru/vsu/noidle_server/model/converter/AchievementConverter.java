package ru.vsu.noidle_server.model.converter;

import ru.vsu.noidle_server.model.Achievement;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class AchievementConverter implements AttributeConverter<Achievement, String> {

    @Override
    public String convertToDatabaseColumn(Achievement achievement) {
        return achievement == null ? null : achievement.toString();
    }

    @Override
    public Achievement convertToEntityAttribute(String name) {
        return Achievement.byName(name);
    }
}
