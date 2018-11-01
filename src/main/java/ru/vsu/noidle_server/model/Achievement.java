package ru.vsu.noidle_server.model;

public enum Achievement {
    TIME("time"), SYMBOL("symbol"), TEST("test");

    private String name;

    Achievement(String name) {
        this.name = name;
    }

    public static Achievement byName(String name) {
        for (Achievement achievement : Achievement.values()) {
            if (achievement.name.equals(name)) {
                return achievement;
            }
        }
        return null;
    }
}