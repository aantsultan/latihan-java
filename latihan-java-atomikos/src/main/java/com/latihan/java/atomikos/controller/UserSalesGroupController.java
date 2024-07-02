package com.latihan.java.atomikos.controller;

import com.latihan.java.atomikos.dto.hts.UserDto;
import com.latihan.java.atomikos.service.UserSalesGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserSalesGroupController {

    private final UserSalesGroupService service;

    @Autowired
    public UserSalesGroupController(UserSalesGroupService service) {
        this.service = service;
    }


    @PostMapping("/user")
    public String save(@RequestBody UserDto dto) {
        return service.save(dto);
    }
}
