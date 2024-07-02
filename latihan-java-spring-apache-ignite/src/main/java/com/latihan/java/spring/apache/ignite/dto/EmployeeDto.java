package com.latihan.java.spring.apache.ignite.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public @Data class EmployeeDto {

    private Integer id;
    private String name;
    private boolean isEmployed;

}
