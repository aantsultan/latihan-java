package com.latihan.java.spring.security.form.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String homeWeb(){
        return "home/view";
    }

    @GetMapping("/mobile")
    public String homeMobile(){
        return "home/mobile_view";
    }

}
