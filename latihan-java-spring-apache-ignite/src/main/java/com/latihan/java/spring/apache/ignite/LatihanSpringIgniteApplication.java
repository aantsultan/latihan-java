package com.latihan.java.spring.apache.ignite;

import org.apache.ignite.springdata.repository.config.EnableIgniteRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableIgniteRepositories
public class LatihanSpringIgniteApplication {

    public static void main(String[] args) {
        SpringApplication.run(LatihanSpringIgniteApplication.class, args);
    }

}
