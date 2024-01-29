package com.latihan.java.spring.data.redis.dto;

import com.latihan.java.spring.data.redis.util.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public @Data class StudentDto {

    private String id;
    private String name;
    private Gender gender;
    private int grade;


}
