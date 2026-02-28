package com.latihan.java.multiple.database.controller;

import com.latihan.java.multiple.database.dto.UserSalesGroupDto;
import com.latihan.java.multiple.database.service.UserSalesGroupService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserSalesGroupController {

    private final UserSalesGroupService service;

    public UserSalesGroupController(UserSalesGroupService service) {
        this.service = service;
    }


    @PostMapping(value = "/api/user-salesgroup",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> save(@RequestBody UserSalesGroupDto dto) {
        String result = service.save(dto);
        return ResponseEntity.ok(result);
    }
}
