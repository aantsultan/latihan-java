package com.latihan.java.spring.apache.ignite.service;

import com.latihan.java.spring.apache.ignite.dto.EmployeeDto;
import com.latihan.java.spring.apache.ignite.dto.ResponseDto;
import org.springframework.http.ResponseEntity;

public interface EmployeeService {

    ResponseEntity<ResponseDto> get(Integer id);

    ResponseEntity<String> save(EmployeeDto dto);
}
