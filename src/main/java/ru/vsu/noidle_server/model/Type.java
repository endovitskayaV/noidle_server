package ru.vsu.noidle_server.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(of = "shortcut")
public enum Type {
    TIME("time"),
    SYMBOL("symbol"),
    COMMIT("commit"),
    EXEC("exec");

    private String shortcut;

    Type(String shortcut) {
        this.shortcut = shortcut;
    }

    public static Type byShortcut(String name) {
        for (Type type : Type.values()) {
            if (type.shortcut.equals(name)) {
                return type;
            }
        }
        return null;
    }

}