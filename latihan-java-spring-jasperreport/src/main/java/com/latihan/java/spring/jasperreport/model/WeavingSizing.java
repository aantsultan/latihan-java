package com.latihan.java.spring.jasperreport.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "s_weaving_sizing")
public class WeavingSizing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String bulanTahun;
    private String tanggal;
    private String jenisSetKain;
    private String jenisKain;
    private String noBenang;
    private Integer beamKe;
    private String noBeam;
    private Integer jumlahPcsPerBeam;

}
