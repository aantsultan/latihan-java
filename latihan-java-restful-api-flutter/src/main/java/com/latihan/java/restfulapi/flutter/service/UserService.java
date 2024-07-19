package com.latihan.java.restfulapi.flutter.service;

import com.latihan.java.restfulapi.flutter.dto.request.CreateUserRequest;
import com.latihan.java.restfulapi.flutter.dto.request.LoginRequest;
import com.latihan.java.restfulapi.flutter.dto.response.TokenResponse;
import com.latihan.java.restfulapi.flutter.dto.response.UserResponse;
import com.latihan.java.restfulapi.flutter.dto.response.WebResponse;
import com.latihan.java.restfulapi.flutter.model.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public interface UserService {

    WebResponse<String> create(CreateUserRequest request);

    WebResponse<TokenResponse> auth(LoginRequest request);

    WebResponse<UserResponse> get();

    WebResponse<TokenResponse> token();

    void validate(CreateUserRequest request);

    void extendExpiredToken(User user);

    Optional<User> findByToken(String token);

    Authentication authenticate(User user);
    Authentication authenticate(User user, HttpServletRequest request);

}
