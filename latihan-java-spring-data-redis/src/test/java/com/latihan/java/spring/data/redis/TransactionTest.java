package com.latihan.java.spring.data.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TransactionTest {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    void transactionTest(){
        redisTemplate.execute(new SessionCallback<Object>() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                operations.multi();
                operations.opsForValue().set("test1", "aant", Duration.ofSeconds(2));
                operations.opsForValue().set("test2", "sultan", Duration.ofSeconds(2));
                operations.exec();
                return null;
            }
        });

        assertEquals("aant", redisTemplate.opsForValue().get("test1"));
        assertEquals("sultan", redisTemplate.opsForValue().get("test2"));
    }

}
