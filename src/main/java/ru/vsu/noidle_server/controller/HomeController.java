package ru.vsu.noidle_server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.vsu.noidle_server.utils.AuthUtils;

@Controller
public class HomeController {

    @GetMapping("/")
    public ModelAndView getIndexPage() {
        return AuthUtils.getUser() == null ? new ModelAndView("index") : new ModelAndView("redirect:/dashboard");
    }

    @GetMapping("/about")
    public String getAboutPage() {
        return "about";
    }
}
