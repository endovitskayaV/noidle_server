package ru.vsu.noidle_server.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(of = "shortcut")
public enum StatisticsSubType {
    PER_LIFE("per_life"),
    PER_DAY("per_day"),
    CONTINUOUS_PER_LIFE("continuous_per_life"),
    CONTINUOUS_PER_DAY("continuous_per_day"),
    SINGLE_KEY("single_key");

    private String shortcut;

    StatisticsSubType(String shortcut) {
        this.shortcut = shortcut;
    }

    public static StatisticsSubType byShortcut(String name) {
        for (StatisticsSubType statisticsSubType : StatisticsSubType.values()) {
            if (statisticsSubType.shortcut.equals(name)) {
                return statisticsSubType;
            }
        }
        return null;
    }
}
