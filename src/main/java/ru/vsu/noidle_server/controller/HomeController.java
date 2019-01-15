package ru.vsu.noidle_server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.vsu.noidle_server.model.SecurityRole;
import ru.vsu.noidle_server.service.SetupService;
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
            } else if (user.getAuthorities().contains(new SimpleGrantedAuthority(SecurityRole.ROLE_ADMIN))) {
                return new ModelAndView("redirect:/admin/users/save");
            } else {
                return new ModelAndView("redirect:/dashboard");
            }

        } else {
            return new ModelAndView("redirect:/admin/setup");
        }
    }

    @GetMapping("/about")
    public String getAboutPage(ModelMap modelMap) {
        Authentication user = AuthUtils.getUser();
        if (user != null && user.getAuthorities().contains(new SimpleGrantedAuthority(SecurityRole.ROLE_ADMIN))) {
            modelMap.addAttribute("admin", true);
        }
        return "about";
    }
}
