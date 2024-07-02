package com.latihan.java.database.service;

import com.latihan.java.database.dto.UserDto;
import com.latihan.java.database.model.User;

import java.util.List;

public interface UserService {

    void save(User user);

    List<UserDto> findAll(Integer page);
}
