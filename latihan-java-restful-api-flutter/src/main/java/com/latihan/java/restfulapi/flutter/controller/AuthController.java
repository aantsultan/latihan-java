package com.latihan.java.restfulapi.flutter.controller;

import com.latihan.java.restfulapi.flutter.dto.request.LoginRequest;
import com.latihan.java.restfulapi.flutter.dto.response.TokenResponse;
import com.latihan.java.restfulapi.flutter.dto.response.WebResponse;
import com.latihan.java.restfulapi.flutter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UserService service;

    @PostMapping(path = "/api/auth/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<TokenResponse> auth(@RequestBody LoginRequest request) {
        return service.auth(request);
    }

}
