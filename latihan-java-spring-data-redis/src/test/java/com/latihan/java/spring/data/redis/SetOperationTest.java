package com.latihan.java.spring.data.redis;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Set;

import static org.hamcrest.Matchers.hasItems;

@SpringBootTest
public class SetOperationTest {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    void setTest(){
        SetOperations<String, String> operations = redisTemplate.opsForSet();
        operations.add("students", "aant");
        operations.add("students", "aant");
        operations.add("students", "sultan");
        operations.add("students", "sultan");
        operations.add("students", "rahmanya");
        operations.add("students", "rahmanya");

        Set<String> students = operations.members("students");
        Assertions.assertEquals(3, students.size());
        MatcherAssert.assertThat(students, hasItems("aant", "sultan", "rahmanya"));
        redisTemplate.delete("students");
    }

}
