package ru.vsu.noidle_server.model;

public enum Achievement {
    TIME("time"), SYMBOL("symbol"), TEST("test");

    private String shortcut;

    Achievement(String shortcut) {
        this.shortcut = shortcut;
    }

    public static Achievement byShortcut(String name) {
        for (Achievement achievement : Achievement.values()) {
            if (achievement.shortcut.equals(name)) {
                return achievement;
            }
        }
        return null;
    }
}