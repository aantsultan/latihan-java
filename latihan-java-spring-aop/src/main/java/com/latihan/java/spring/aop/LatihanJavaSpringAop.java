package com.latihan.java.spring.aop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
public class LatihanJavaSpringAop {

    public static void main(String[] args) {
        SpringApplication.run(LatihanJavaSpringAop.class, args);
    }
}
