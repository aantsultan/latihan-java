package com.latihan.java.database.controller;

import com.latihan.java.database.dto.UserDto;
import com.latihan.java.database.model.SalesGroup;
import com.latihan.java.database.service.UserSalesGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
