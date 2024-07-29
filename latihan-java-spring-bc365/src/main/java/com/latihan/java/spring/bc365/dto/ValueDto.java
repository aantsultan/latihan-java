package com.latihan.java.spring.bc365.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValueDto {

    private String odataEtag;
    private String no;
    private String description;
    private String billToCustomerNo;
    private String status;
    private String personResponsible;
    private String searchDescription;
    private String projectManager;
}
