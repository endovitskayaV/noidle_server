package ru.vsu.noidle_server.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthUtils {
    public static Authentication getUser() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
