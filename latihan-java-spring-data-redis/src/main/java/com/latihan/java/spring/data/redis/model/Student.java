package com.latihan.java.spring.data.redis.model;

import com.latihan.java.spring.data.redis.util.Gender;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("Student")
@Builder
public @Data class Student implements Serializable {

    @Id
    private String id;
    private String name;
    private Gender gender;
    private int grade;

}
