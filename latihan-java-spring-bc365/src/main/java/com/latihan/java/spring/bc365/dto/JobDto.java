package com.latihan.java.spring.bc365.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.latihan.java.spring.bc365.model.Value;
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
    private List<Value> value = new ArrayList<>();

}
