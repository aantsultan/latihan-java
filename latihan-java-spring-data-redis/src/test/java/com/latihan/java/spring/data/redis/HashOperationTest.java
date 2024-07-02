package com.latihan.java.spring.data.redis;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class HashOperationTest {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    void hashSetTest() {
        HashOperations<String, Object, Object> op = redisTemplate.opsForHash();
//        op.put("user:1", "id", "1");
//        op.put("user:1", "name", "aant");
//        op.put("user:1", "email", "aant@mail.com");

        Map<Object, Object> user = new HashMap<>();
        user.put("id", "1");
        user.put("name", "aant");
        user.put("email", "aant@mail.com");
        op.putAll("user:1", user);

        Assertions.assertEquals("1", op.get("user:1", "id"));
        Assertions.assertEquals("aant", op.get("user:1", "name"));
        Assertions.assertEquals("aant@mail.com", op.get("user:1", "email"));

        redisTemplate.delete("user:1");
    }

}
