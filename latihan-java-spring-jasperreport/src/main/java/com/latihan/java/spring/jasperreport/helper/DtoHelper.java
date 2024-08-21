package com.latihan.java.spring.jasperreport.helper;

import com.latihan.java.spring.jasperreport.dto.WeavingSizingDto;
import com.latihan.java.spring.jasperreport.dto.WeavingSizingReportDto;
import com.latihan.java.spring.jasperreport.model.WeavingSizing;

public class DtoHelper {

    private DtoHelper(){}

    public static WeavingSizingDto toWeavingSizingDto(WeavingSizing weavingSizing){
        return WeavingSizingDto.builder()
                .id(weavingSizing.getId())
                .tanggal(weavingSizing.getTanggal())
                .jenisSetKain(weavingSizing.getJenisSetKain())
                .jenisKain(weavingSizing.getJenisKain())
                .noBenang(weavingSizing.getNoBenang())
                .beamKe(weavingSizing.getBeamKe())
                .noBeam(weavingSizing.getNoBeam())
                .jumlahPcsPerBeam(weavingSizing.getJumlahPcsPerBeam())
                .bulanTahun(weavingSizing.getBulanTahun())
                .build();
    }

    public static WeavingSizingReportDto toWeavingSizingReportDto(WeavingSizing weavingSizing){
        return WeavingSizingReportDto.builder()
                .id(weavingSizing.getId())
                .tanggal(weavingSizing.getTanggal())
                .jenisSetKain(weavingSizing.getJenisSetKain())
                .jenisKain(weavingSizing.getJenisKain())
                .noBenang(weavingSizing.getNoBenang())
                .bulanTahun(weavingSizing.getBulanTahun())
                .build();
    }

}
