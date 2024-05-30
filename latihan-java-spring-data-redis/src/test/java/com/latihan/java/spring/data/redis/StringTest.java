package com.latihan.java.spring.data.redis;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
public class StringTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void redisTemplateTest(){
        Assertions.assertNotNull(stringRedisTemplate);
    }
}
