package com.latihan.java.database.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
public @Data class UserDto {

    private Long id;
    private String name;
    private String email;
    private List<SalesGroupDto> salesGroup;
}
