package com.latihan.java.spring.jasperreport.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeavingSizingReportDto {

    private Long id;
    private String bulanTahun;
    private String tanggal;
    private String jenisSetKain;
    private String jenisKain;
    private String noBenang;
    private BeamKeDto beamKe1;
    private BeamKeDto beamKe2;
    private BeamKeDto beamKe3;

}
