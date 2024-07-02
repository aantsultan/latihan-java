package com.latihan.java.spring.data.redis;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

@SpringBootTest
class ZSetOperationTest {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    void zSetTest(){
        ZSetOperations<String, String> operations = redisTemplate.opsForZSet();
        operations.add("score", "aant", 100);
        operations.add("score", "sultan", 50);
        operations.add("score", "rahmanya", 60);

        Assertions.assertEquals("aant", operations.popMax("score").getValue());
        Assertions.assertEquals("rahmanya", operations.popMax("score").getValue());
        Assertions.assertEquals("sultan", operations.popMax("score").getValue());

        redisTemplate.delete("score");
    }

}
