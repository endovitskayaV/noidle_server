package ru.vsu.noidle_server.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(of = "shortcut")
public enum StatisticsType {
    TIME("time"),
    SYMBOL("symbol", "symbols"),
    COMMIT("commit", "commits"),
    EXEC("exec", "executions"),
    LANG_TIME("lang_time", "time in"),
    LANG_SYMBOL("lang_symbol", "symbols in"),
    SINGLE_KEY("single_key", "key quantity");

    private String shortcut;

    private String dto;

    StatisticsType(String shortcut, String dto) {
        this.shortcut = shortcut;
        this.dto=dto;
    }

    StatisticsType(String shortcut) {
        this.shortcut = shortcut;
        this.dto=shortcut;
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