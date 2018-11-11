package ru.vsu.noidle_server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import ru.vsu.noidle_server.service.UserService;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/oauth")
    public RedirectView user(OAuth2Authentication user) {
        userService.saveUser(user);
        return new RedirectView("/");
    }

//    @GetMapping("/callback/jetbrains")
//    public RedirectView user1(@RequestParam(name = "code") String code, @RequestParam(name = "state") String state) {
//        return new RedirectView("/");
//    }

}
