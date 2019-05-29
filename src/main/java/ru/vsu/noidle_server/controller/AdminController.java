package ru.vsu.noidle_server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.vsu.noidle_server.Constants;
import ru.vsu.noidle_server.exception.ServiceException;
import ru.vsu.noidle_server.service.SetupService;
import ru.vsu.noidle_server.service.UserService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final SetupService setupService;
    private final UserService userService;

    @GetMapping("/setup")
    public String getSetup(ModelMap modelMap) {
        modelMap.addAttribute("login", Constants.ADMIN_NAME);
        modelMap.addAttribute("doNotLogin", true);
        return "setup";
    }

    @PostMapping("/setup")
    public ModelAndView postSetup(String password) {
        try {
            setupService.finishSetup(password);
        } catch (ServiceException e) {
            //TODO: handle somehow
            e.printStackTrace();
        }
        return new ModelAndView("redirect:/login?afterSetup=true");
    }

    @GetMapping("/users/save")
    public String saveUsers(ModelMap modelMap) {
        modelMap.addAttribute("admin", true);
        return "add_users";
    }

    //@Secured("ROLE_ADMIN")
    @PostMapping("/users/save")
    public String saveUsers(String userData, ModelMap modelMap) {
        try {
            userService.saveUsers(userData);
            modelMap.addAttribute("message","Success");
            modelMap.addAttribute("success",true);
        } catch (ServiceException e) {
            modelMap.addAttribute("message",e.getMessage());
            modelMap.addAttribute("success",false);
        }

        modelMap.addAttribute("admin", true);
        return "users_save_result";
    }
}
