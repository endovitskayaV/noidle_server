package ru.vsu.noidle_server.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(of = "shortcut")
public enum UpdateRole {
    ADMIN("admin"),
    USER("user");

    public static final String ROLE_ADMIN = "admin";

    private String shortcut;

    UpdateRole(String shortcut) {
        this.shortcut = shortcut;
    }

    public static UpdateRole byShortcut(String name) {
        for (UpdateRole updateRole : UpdateRole.values()) {
            if (updateRole.shortcut.equals(name)) {
                return updateRole;
            }
        }
        return null;
    }
}

