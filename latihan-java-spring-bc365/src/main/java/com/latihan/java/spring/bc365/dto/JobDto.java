package com.latihan.java.spring.bc365.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobDto {

    private String odataContext;
    private List<ValueDto> value = new ArrayList<>();

}
