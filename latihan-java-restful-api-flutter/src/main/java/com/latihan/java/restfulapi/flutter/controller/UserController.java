package com.latihan.java.restfulapi.flutter.controller;

import com.latihan.java.restfulapi.flutter.dto.request.CreateUserRequest;
import com.latihan.java.restfulapi.flutter.dto.response.UserResponse;
import com.latihan.java.restfulapi.flutter.dto.response.WebResponse;
import com.latihan.java.restfulapi.flutter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping(path = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<String> register(@RequestBody CreateUserRequest request) {
        return service.create(request);
    }

    @GetMapping(path = "/api/users/current", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<UserResponse> get(){
        return service.get();
    }

}
