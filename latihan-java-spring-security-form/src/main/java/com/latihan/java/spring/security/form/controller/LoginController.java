package com.latihan.java.spring.security.form.controller;

import com.latihan.java.spring.security.form.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class LoginController {

    @GetMapping("/login")
    public String loginWeb() {
        return "login/view";
    }

    @GetMapping("/logout-successful")
    public String logoutSuccessfulWeb() {
        return "logout/view";
    }

    @GetMapping("/mobile/login")
    public String loginMobile(Model model,
                              @RequestParam(value = "username", required = false) String username,
                              @RequestParam(value = "password", required = false) String password) {
        UserDto userDto = new UserDto();
        userDto.setUsername(username);
        userDto.setPassword(password);
        model.addAttribute("userDto", userDto);
        return "login/mobile_view";
    }

    @GetMapping("/mobile/logout-successful")
    public String logoutSuccessfulMobile() {
        return "logout/mobile_view";
    }
}
