package com.latihan.java.spring.bc365.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(value = {"odataEtag"})
public class SampleUserListDto {

    private String odataEtag;
    private Integer uid;
    private String email;
    private String address;
    private Integer phone;
    private String ulangTahun;

}
