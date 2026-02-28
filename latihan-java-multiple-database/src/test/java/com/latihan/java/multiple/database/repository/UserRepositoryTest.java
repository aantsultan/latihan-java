package com.latihan.java.multiple.database.repository;

import com.latihan.java.multiple.database.model.hts.User;
import com.latihan.java.multiple.database.repository.hts.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Test
    void findAll(){
        List<User> users = repository.findAll();
        Assertions.assertNotNull(users);
    }

}
