package ru.vsu.noidle_server.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(of = "shortcut")
public enum SubType {
    PER_LIFE("per_life"),
    PER_DAY("per_day"),
    CONTINUOUS_PER_LIFE("continuous_per_life"),
    CONTINUOUS_PER_DAY("continuous_per_day"),
    SINGLE_KEY("single_key");

    private String shortcut;

    SubType(String shortcut) {
        this.shortcut = shortcut;
    }

    public static SubType byShortcut(String name) {
        for (SubType subType : SubType.values()) {
            if (subType.shortcut.equals(name)) {
                return subType;
            }
        }
        return null;
    }
}
