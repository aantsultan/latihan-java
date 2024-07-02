package com.latihan.java.database.dto;

import lombok.Builder;
import lombok.Data;

@Builder
public @Data class SalesGroupDto {

    private Long id;
    private String name;

}
