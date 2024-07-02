package com.latihan.java.spring.mongodb.service;

import com.latihan.java.spring.mongodb.dto.CustomerDto;
import com.latihan.java.spring.mongodb.dto.ResponseDto;
import org.springframework.http.ResponseEntity;

public interface CustomerService {
    ResponseEntity<ResponseDto> get();

    String save(CustomerDto customerDto);
}
