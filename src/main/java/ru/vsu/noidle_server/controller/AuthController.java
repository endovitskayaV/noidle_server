package ru.vsu.noidle_server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.vsu.noidle_server.exception.ServiceException;
import ru.vsu.noidle_server.model.dto.UserDto;
import ru.vsu.noidle_server.service.UserService;
import ru.vsu.noidle_server.utils.AuthUtils;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/oauth")
    public ModelAndView user(OAuth2Authentication user, ModelMap modelMap) {
        try {
            userService.save(user);
            return new ModelAndView("redirect:/dashboard");
        } catch (ServiceException e) {
            modelMap.addAttribute("errorMessage", e.getMessage());
            return new ModelAndView("dashboard", modelMap);
        }
    }

    @RequestMapping(value = "/isauth", method = RequestMethod.GET)
    public ResponseEntity<UserDto> currentUserName() {
        return AuthUtils.getUser() == null ? ResponseEntity.notFound().build() : ResponseEntity.noContent().build();
    }

}
