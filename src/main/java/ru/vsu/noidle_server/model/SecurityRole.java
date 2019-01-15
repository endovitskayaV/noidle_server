package ru.vsu.noidle_server.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(of = "shortcut")
public enum SecurityRole {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";
    public static final String PREFIX = "ROLE_";

    private String shortcut;

    SecurityRole(String shortcut) {
        this.shortcut = shortcut;
    }

    public static SecurityRole byShortcut(String name) {
        for (SecurityRole securityRole : SecurityRole.values()) {
            if (securityRole.shortcut.equals(name)) {
                return securityRole;
            }
        }
        return USER;
    }

    public static SecurityRole byUpdateRole(UpdateRole updateRole) {
        for (SecurityRole securityRole : SecurityRole.values()) {
            if (securityRole.shortcut.toLowerCase().substring(PREFIX.length()).equals(updateRole.getShortcut())) {
                return securityRole;
            }
        }
        return USER;
    }
}
