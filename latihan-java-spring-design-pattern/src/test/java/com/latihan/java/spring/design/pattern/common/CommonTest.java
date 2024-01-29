package com.latihan.java.spring.design.pattern.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CommonTest {

    @Test
    void user() throws JsonProcessingException {
        User user = User.builder()
                .name("aant")
                .email("aant@mail.com")
                .build();
        System.out.println(user);
    }

}
