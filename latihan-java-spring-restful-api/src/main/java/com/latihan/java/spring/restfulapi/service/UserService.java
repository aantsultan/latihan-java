package com.latihan.java.spring.restfulapi.service;

import com.latihan.java.spring.restfulapi.entity.User;
import com.latihan.java.spring.restfulapi.model.RegisterUserRequest;
import com.latihan.java.spring.restfulapi.model.UserResponse;

public interface UserService {

    void register(RegisterUserRequest request);

    UserResponse get(User user);
}
