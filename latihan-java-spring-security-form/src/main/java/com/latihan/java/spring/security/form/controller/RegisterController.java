package com.latihan.java.spring.security.form.controller;

import com.latihan.java.spring.security.form.dto.UserDto;
import com.latihan.java.spring.security.form.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class RegisterController {

    private final UserService userService;

    @GetMapping("/register")
    public String register(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("userDto", userDto);
        return "register/view";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("userDto") UserDto userDto) {
        userService.create(userDto);
        return "register/success_register_view";
    }
}
