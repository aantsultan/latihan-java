package com.latihan.java.spring.data.redis;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.time.Duration;

@SpringBootTest
class ValueOperationTest {

    @Autowired
    private StringRedisTemplate template;

    @Test
    void valueTest() throws InterruptedException {
        ValueOperations<String, String> operations = template.opsForValue();
        operations.set("name", "aant", Duration.ofSeconds(2));
        Assertions.assertEquals("aant", operations.get("name"));

        Thread.sleep(Duration.ofSeconds(3).toMillis());
        Assertions.assertNull(operations.get("name"));
    }
}