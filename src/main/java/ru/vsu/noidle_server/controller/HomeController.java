package ru.vsu.noidle_server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String getIndexPage() {
        return AuthUtils.getUser() == null ? "index" : "dashboard";
    }
}