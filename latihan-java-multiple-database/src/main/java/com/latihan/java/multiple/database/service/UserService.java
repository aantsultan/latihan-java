package com.latihan.java.multiple.database.service;

import com.latihan.java.multiple.database.dto.hts.UserDto;
import com.latihan.java.multiple.database.model.hts.User;

import java.util.List;

public interface UserService {

    void save(User user);

    List<UserDto> findAll();

    void deleteAll();
}
