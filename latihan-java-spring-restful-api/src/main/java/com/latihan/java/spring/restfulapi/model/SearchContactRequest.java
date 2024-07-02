package com.latihan.java.spring.restfulapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchContactRequest {

    private String name;
    private String email;
    private String phone;

    @NotNull
    private Integer page;

    @NotNull
    private Integer size;

}
