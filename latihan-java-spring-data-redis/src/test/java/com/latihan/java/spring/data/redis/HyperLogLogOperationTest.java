package com.latihan.java.spring.data.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HyperLogLogOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class HyperLogLogOperationTest {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    void hyperLogLogTest(){
        HyperLogLogOperations<String, String> hyper = redisTemplate.opsForHyperLogLog();
        hyper.add("traffics", "aant", "sultan", "rahmanya");
        hyper.add("traffics", "aant", "elaina", "kiana");
        hyper.add("traffics", "aant", "sultan", "seele");

        assertEquals(6L, hyper.size("traffics"));
    }
}
