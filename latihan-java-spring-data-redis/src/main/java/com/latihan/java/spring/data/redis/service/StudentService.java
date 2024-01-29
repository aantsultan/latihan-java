package com.latihan.java.spring.data.redis.service;

import com.latihan.java.spring.data.redis.dto.ResponseDto;
import com.latihan.java.spring.data.redis.dto.StudentDto;
import org.springframework.http.ResponseEntity;

public interface StudentService {
    ResponseEntity<ResponseDto> get();

    ResponseEntity<String> save(StudentDto request);

    ResponseEntity<StudentDto> findById(String id);
}
