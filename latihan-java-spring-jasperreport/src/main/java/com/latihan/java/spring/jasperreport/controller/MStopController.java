package com.latihan.java.spring.jasperreport.controller;

import com.latihan.java.spring.jasperreport.service.MStopService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;

@RestController
@RequestMapping("/mstop")
@RequiredArgsConstructor
public class MStopController {

    private final MStopService mStopService;

    @GetMapping(path = "/laporan-kerja/excel")
    public ResponseEntity<Resource> excel() {
        Resource result = mStopService.generateExcel();
        return ResponseEntity.ok()
                .header(CONTENT_DISPOSITION, "attachment; filename=" + "user_report"
                        + System.currentTimeMillis() + ".xlsx")
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(result);
    }
}
