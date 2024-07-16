package com.latihan.java.restfulapi.flutter.service;

import com.latihan.java.restfulapi.flutter.dto.request.CreateUserRequest;
import com.latihan.java.restfulapi.flutter.dto.request.LoginRequest;
import com.latihan.java.restfulapi.flutter.dto.response.TokenResponse;
import com.latihan.java.restfulapi.flutter.dto.response.UserResponse;
import com.latihan.java.restfulapi.flutter.dto.response.WebResponse;
import com.latihan.java.restfulapi.flutter.model.User;

public interface UserService {

    WebResponse<String> create(CreateUserRequest request);

    WebResponse<TokenResponse> auth(LoginRequest request);

    WebResponse<UserResponse> get();

    void validate(CreateUserRequest request);

}
