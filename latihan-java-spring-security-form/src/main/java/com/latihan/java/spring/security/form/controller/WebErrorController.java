package com.latihan.java.spring.security.form.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@ControllerAdvice
@Slf4j
public class WebErrorController implements ErrorController {

    @ExceptionHandler({RuntimeException.class})
    @RequestMapping("/error")
    public String errorHandler(Model model, Exception exception) {
        model.addAttribute("message", exception.getMessage());
        return "error/view";
    }

    @RequestMapping(value = "/403")
    public String accessDenied(Model model, Principal principal) {
        String message = "Authorization is required to access this resource.";
        model.addAttribute("message", message);
        return "error/page_403";
    }
}
