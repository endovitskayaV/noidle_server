package ru.vsu.noidle_server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;
import ru.vsu.noidle_server.model.dto.LoginDto;
import ru.vsu.noidle_server.service.AuthenticationService;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping(value = "/login")
    public ModelAndView login(LoginDto loginDto, ModelMap modelMap) {
        String encoded = new BCryptPasswordEncoder().encode(loginDto.getPassword());
        System.out.println(encoded);
        if (authenticationService.login(loginDto)) {
            return new ModelAndView("redirect:/dashboard");
        } else {
            modelMap.addAttribute("errorMessage", "Incorrect login or password");
            modelMap.addAttribute("login", loginDto.getName());
            return new ModelAndView("login", modelMap);
        }
    }
}
