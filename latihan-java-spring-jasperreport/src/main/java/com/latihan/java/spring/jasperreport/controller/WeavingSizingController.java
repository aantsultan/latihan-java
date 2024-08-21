package com.latihan.java.spring.jasperreport.controller;

import com.latihan.java.spring.jasperreport.dto.CreateWeavingSizingRequest;
import com.latihan.java.spring.jasperreport.dto.WeavingSizingDto;
import com.latihan.java.spring.jasperreport.dto.WebResponse;
import com.latihan.java.spring.jasperreport.service.WeavingSizingService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;

@RestController
@RequestMapping(path = "/sizing")
@RequiredArgsConstructor
public class WeavingSizingController {

    private final WeavingSizingService weavingSizingService;

    @PostMapping
    public WebResponse<String> save(@RequestBody CreateWeavingSizingRequest request) {
        String result = weavingSizingService.save(request);
        return WebResponse.<String>builder().data(result).build();
    }

    @GetMapping
    public WebResponse<List<WeavingSizingDto>> getAll() {
        List<WeavingSizingDto> result = weavingSizingService.getAll();
        return WebResponse.<List<WeavingSizingDto>>builder()
                .data(result).build();
    }

    @GetMapping(path = "/excel")
    public ResponseEntity<Resource> excel() {
        Resource result = weavingSizingService.generateExcel();
        return ResponseEntity.ok()
                .header(CONTENT_DISPOSITION, "attachment; filename=" + "user_report"
                        + System.currentTimeMillis() + ".xlsx")
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(result);
    }

}
