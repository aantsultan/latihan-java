package com.latihan.java.spring.bc365.controller;

import com.latihan.java.spring.bc365.dto.JobDto;
import com.latihan.java.spring.bc365.dto.ODataV4Dto;
import com.latihan.java.spring.bc365.dto.WebResponse;
import com.latihan.java.spring.bc365.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @GetMapping("/api/jobs")
    public ResponseEntity<WebResponse<ODataV4Dto<JobDto>>> get() {
        ODataV4Dto<JobDto> dto = jobService.jobList();
        return ResponseEntity.ok(WebResponse.<ODataV4Dto<JobDto>>builder()
                .data(dto)
                .build());
    }

}
