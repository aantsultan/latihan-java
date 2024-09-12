package com.latihan.java.spring.postgresql.jsonb.service;

import com.latihan.java.spring.postgresql.jsonb.dto.ApiResponseDto;
import com.latihan.java.spring.postgresql.jsonb.dto.UserRequestDto;
import com.latihan.java.spring.postgresql.jsonb.dto.UserResponseDto;

import java.util.List;

public interface UserService {
    ApiResponseDto<String> save(UserRequestDto requestDto);

    ApiResponseDto<List<UserResponseDto>> findAll();
}
