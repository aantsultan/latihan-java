package com.latihan.java.database.controller;

import com.latihan.java.database.dto.UserDto;
import com.latihan.java.database.model.User;
import com.latihan.java.database.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }


    @GetMapping("/user/{page}")
    public List<UserDto> findAll(@PathVariable("page") Integer page){
        return service.findAll(page);
    }
}
