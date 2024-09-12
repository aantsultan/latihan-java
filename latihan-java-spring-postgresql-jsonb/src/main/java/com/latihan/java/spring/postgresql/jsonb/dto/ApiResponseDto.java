package com.latihan.java.spring.postgresql.jsonb.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class ApiResponseDto<T> {

    private T data;
    private String message;

}
