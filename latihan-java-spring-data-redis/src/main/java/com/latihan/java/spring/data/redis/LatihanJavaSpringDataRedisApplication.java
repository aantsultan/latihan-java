package com.latihan.java.spring.data.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
@EnableRedisRepositories(basePackages = "com.latihan.java.spring.data.redis")
public class LatihanJavaSpringDataRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(LatihanJavaSpringDataRedisApplication.class, args);
    }

}
