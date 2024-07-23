package com.latihan.java.spring.oauth2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping(path = "/api/users")
    public String get(){
        return "Hello Users";
    }

}
