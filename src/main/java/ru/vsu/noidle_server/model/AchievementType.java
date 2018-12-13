package ru.vsu.noidle_server.model;

import lombok.Getter;
import lombok.ToString;


@Getter
@ToString(of = "shortcut")
public enum AchievementType {
    LEVEL("level"),
    EXTRA("extra"),
    TEAM("team");

    private String shortcut;

    AchievementType(String shortcut) {
        this.shortcut = shortcut;
    }

    public static AchievementType byShortcut(String name) {
        for (AchievementType achievementType : AchievementType.values()) {
            if (achievementType.shortcut.equals(name)) {
                return achievementType;
            }
        }
        return null;
    }
}
