package com.latihan.java.spring.security.form.service;

import com.latihan.java.spring.security.form.dto.UserDto;
import com.latihan.java.spring.security.form.model.User;

public interface UserService {

    User create(UserDto userDto);


}
