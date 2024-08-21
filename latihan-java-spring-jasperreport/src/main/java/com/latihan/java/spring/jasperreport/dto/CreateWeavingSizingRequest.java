package com.latihan.java.spring.jasperreport.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateWeavingSizingRequest {

    private String bulanTahun;
    private String tanggal;
    private String jenisSetKain;
    private String jenisKain;
    private String noBenang;
    private Integer beamKe;
    private String noBeam;
    private Integer jumlahPcsPerBeam;

}
