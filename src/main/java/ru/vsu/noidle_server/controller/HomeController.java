package ru.vsu.noidle_server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.vsu.noidle_server.Constants;
import ru.vsu.noidle_server.model.UpdateRole;
import ru.vsu.noidle_server.service.SetupService;
import ru.vsu.noidle_server.service.UserService;
import ru.vsu.noidle_server.utils.AuthUtils;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final SetupService setupService;

    @GetMapping("/")
    public ModelAndView getIndexPage() {
        if (setupService.setupFinished()) {

            Authentication user = AuthUtils.getUser();
            if (user == null) {
                return new ModelAndView("index");
            } else if (user.getAuthorities().contains(new SimpleGrantedAuthority(UpdateRole.ROLE_ADMIN))) {
                return new ModelAndView("redirect:/users/save");
            }else{
                return new ModelAndView("redirect:/dashboard");
            }

        } else {
            return new ModelAndView("redirect:/setup");
        }

    }

    @GetMapping("/about")
    public String getAboutPage() {
        return "about";
    }

    @GetMapping("/setup")
    public String getSetup(ModelMap modelMap) {
        modelMap.addAttribute("login", Constants.ADMIN_NAME);
        modelMap.addAttribute("doNotLogin", true);
        return "setup";
    }

    @PostMapping("/setup")
    public String postSetup(String password, ModelMap modelMap) {
        setupService.finishSetup(password);
        modelMap.addAttribute("setupMessage", "Admin account created");
        modelMap.addAttribute("login", Constants.ADMIN_NAME);
        return "login";
    }


}
