package com.latihan.java.spring.bc365.dto.sampleuserlist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateSULDto {

    private String email;
    private String address;
    private Integer phone;
    private String ulangTahun;

}