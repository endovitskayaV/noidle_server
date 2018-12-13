package ru.vsu.noidle_server.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(of = "shortcut")
public enum StatisticsType {
    TIME("time"),
    SYMBOL("symbol"),
    COMMIT("commit"),
    EXEC("exec"),
    LANG_TIME ("lang_time"),
    LANG_SYMBOL ("lang_symbol");

    private String shortcut;

    StatisticsType(String shortcut) {
        this.shortcut = shortcut;
    }

    public static StatisticsType byShortcut(String name) {
        for (StatisticsType statisticsType : StatisticsType.values()) {
            if (statisticsType.shortcut.equals(name)) {
                return statisticsType;
            }
        }
        return null;
    }
}