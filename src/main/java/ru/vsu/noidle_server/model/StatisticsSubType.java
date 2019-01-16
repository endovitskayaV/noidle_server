package ru.vsu.noidle_server.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(of = "shortcut")
public enum StatisticsSubType {
    PER_LIFE("per_life", "overall"),
    PER_DAY("per_day", "per day"),
    CONTINUOUS_PER_LIFE("continuous_per_life", "max continuous overall"),
    CONTINUOUS_PER_DAY("continuous_per_day", "max continuous per day");

    private String shortcut;

    private String dto;

    StatisticsSubType(String shortcut, String dto) {
        this.shortcut = shortcut;
        this.dto = dto;
    }

    StatisticsSubType(String shortcut) {
        this.shortcut = shortcut;
        this.dto = shortcut;
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
