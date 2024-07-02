package com.latihan.java.spring.aop;

import com.latihan.java.spring.aop.service.HelloService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HelloServiceTest {

    @Autowired
    private HelloService helloService;

    @Test
    void helloService() {
        Assertions.assertEquals("Hello Aant", helloService.hello("Aant"));
        Assertions.assertEquals("Hello Aant Sultan", helloService.hello("Aant", "Sultan"));
        Assertions.assertEquals("Bye Aant", helloService.bye("Aant"));

        helloService.test();
    }
}
