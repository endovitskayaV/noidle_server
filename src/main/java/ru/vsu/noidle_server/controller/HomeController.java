package ru.vsu.noidle_server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.vsu.noidle_server.utils.AuthUtils;

@Controller
public class HomeController {

    @GetMapping("/")
    public String getIndexPage() {
        return AuthUtils.getUser() == null ? "index" : "dashboard";
    }

    @GetMapping("/about")
    public String getAboutPage() {
        return "about";
    }
}
