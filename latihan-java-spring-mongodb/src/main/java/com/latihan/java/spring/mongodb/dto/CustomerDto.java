package com.latihan.java.spring.mongodb.dto;

import lombok.Builder;
import lombok.Data;

@Builder
public @Data class CustomerDto {

    private String id;
    private String firstName;
    private String lastName;

}
