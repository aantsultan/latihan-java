package com.latihan.java.spring.data.redis.model;

import com.latihan.java.spring.data.redis.util.Gender;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serial;
import java.io.Serializable;

@Builder
@RedisHash("Student")
public @Data class Student implements Serializable {

    @Serial
    private static final long serialVersionUID = -5872281362851476351L;

    @Id
    private String id;
    private String name;
    private Gender gender;
    private int grade;

}
