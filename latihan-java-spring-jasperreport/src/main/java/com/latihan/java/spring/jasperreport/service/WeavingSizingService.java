package com.latihan.java.spring.jasperreport.service;

import com.latihan.java.spring.jasperreport.dto.CreateWeavingSizingRequest;
import com.latihan.java.spring.jasperreport.dto.WeavingSizingDto;
import org.springframework.core.io.Resource;

import java.util.List;

public interface WeavingSizingService {

    Resource generateExcel();


    String save(CreateWeavingSizingRequest request);

    List<WeavingSizingDto> getAll();
}
