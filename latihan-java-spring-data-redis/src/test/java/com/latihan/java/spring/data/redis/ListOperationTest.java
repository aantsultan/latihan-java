package com.latihan.java.spring.data.redis;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class ListOperationTest {

    @Autowired
    private StringRedisTemplate template;

    @Test
    void listTest(){
        ListOperations<String, String> operations = template.opsForList();
        operations.rightPush("names", "aant");
        operations.rightPush("names", "sultan");
        operations.rightPush("names", "rahmanya");

        Assertions.assertEquals("aant", operations.leftPop("names"));
        Assertions.assertEquals("sultan", operations.leftPop("names"));
        Assertions.assertEquals("rahmanya", operations.leftPop("names"));

        template.delete("names");
    }

}
