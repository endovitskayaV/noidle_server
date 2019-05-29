package ru.vsu.noidle_server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.vsu.noidle_server.Constants;

import javax.servlet.http.HttpServletRequest;

//TODO: implement ControllerAdvice exceptions handling
@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    @GetMapping(Constants.ERROR_URI)
    public ModelAndView getErrorPage(ModelMap modelMap, HttpServletRequest httpServletRequest) {
        modelMap.addAttribute("url", httpServletRequest.getRequestURL().toString()
                .replace(httpServletRequest.getRequestURI(), ""));
        return new ModelAndView("error", modelMap);
    }

    @Override
    public String getErrorPath() {
        return Constants.ERROR_URI;
    }
}
