package com.latihan.java.spring.oracle;

import com.latihan.java.spring.oracle.entity.User;
import com.latihan.java.spring.oracle.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp(){
        userRepository.deleteAll();
    }

    @Test
    void findAll() {
        List<User> users = userRepository.findAll();
        Assertions.assertTrue(users.isEmpty());
    }

    @Test
    void save(){
        User user = new User();
        user.setName("Aant");
        user.setCreatedDate(LocalDateTime.now());
        user.setCreatedBy(0L);
        userRepository.save(user);

        List<User> users = userRepository.findAll();
        Assertions.assertFalse(users.isEmpty());
    }

}
