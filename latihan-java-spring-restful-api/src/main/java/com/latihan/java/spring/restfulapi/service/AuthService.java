package com.latihan.java.spring.restfulapi.service;

import com.latihan.java.spring.restfulapi.entity.User;
import com.latihan.java.spring.restfulapi.model.LoginUserRequest;
import com.latihan.java.spring.restfulapi.model.TokenResponse;

public interface AuthService {

    TokenResponse login(LoginUserRequest request);

    void logout(User user);
}
