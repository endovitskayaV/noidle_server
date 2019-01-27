package ru.vsu.noidle_server.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(of = "shortcut")
public enum UserRole {
    DEV("dev"),
    LEAD("lead");

    private String shortcut;

    UserRole(String shortcut) {
        this.shortcut = shortcut;
    }

    public static UserRole byShortcut(String name) {
        for (UserRole userRole : UserRole.values()) {
            if (userRole.shortcut.equals(name)) {
                return userRole;
            }
        }
        return null;
    }
}
