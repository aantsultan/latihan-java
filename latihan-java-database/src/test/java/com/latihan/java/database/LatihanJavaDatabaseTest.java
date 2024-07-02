package com.latihan.java.database;

import com.latihan.java.database.model.User;
import com.latihan.java.database.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@SpringBootTest
class LatihanJavaDatabaseTest {

    private final UserRepository userRepository;

    @Autowired
    public LatihanJavaDatabaseTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Test
    void testFindAllUser() {
        List<User> users = StreamSupport
                .stream(userRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        Assertions.assertEquals(0, users.size());
    }

    @Test
    void testLatihan(){
        BigInteger result = new BigInteger("7");
        Assertions.assertTrue(result.isProbablePrime(100));
    }

}
