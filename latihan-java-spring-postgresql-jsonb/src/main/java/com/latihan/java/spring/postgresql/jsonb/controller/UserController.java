package com.latihan.java.spring.postgresql.jsonb.controller;

import com.latihan.java.spring.postgresql.jsonb.dto.ApiResponseDto;
import com.latihan.java.spring.postgresql.jsonb.dto.UserRequestDto;
import com.latihan.java.spring.postgresql.jsonb.dto.UserResponseDto;
import com.latihan.java.spring.postgresql.jsonb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    @PostMapping
    public ResponseEntity<ApiResponseDto<String>> save(@RequestBody UserRequestDto requestDto) {
        ApiResponseDto<String> result = service.save(requestDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ApiResponseDto<List<UserResponseDto>>> findAll() {
        ApiResponseDto<List<UserResponseDto>> result = service.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
