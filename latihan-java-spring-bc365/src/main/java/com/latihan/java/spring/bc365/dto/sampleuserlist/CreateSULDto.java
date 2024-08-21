package com.latihan.java.spring.bc365.dto.sampleuserlist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateSULDto {

    @NotNull
    private Integer uid;
    private String email;
    private String address;
    private Integer phone;
    private String ulangTahun;

}
