package com.latihan.java.spring.security.form.service;

import com.latihan.java.spring.security.form.dto.UserDto;
import com.latihan.java.spring.security.form.model.User;
import com.latihan.java.spring.security.form.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User create(UserDto userDto) {
        User user = repository.findByUsername(userDto.getUsername());
        if(user != null){
            throw new RuntimeException("Username already exist");
        }
        user = new User();

        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        try {
            repository.save(user);
        } catch (Exception e){
            throw new RuntimeException("Failed to save user");
        }

        return user;
    }

}
