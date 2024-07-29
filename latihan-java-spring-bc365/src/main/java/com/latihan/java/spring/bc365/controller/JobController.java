package com.latihan.java.spring.bc365.controller;

import com.latihan.java.spring.bc365.dto.JobDto;
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
    public ResponseEntity<WebResponse<JobDto>> get() {
        JobDto jobDto = jobService.jobList();
        return ResponseEntity.ok(WebResponse.<JobDto>builder()
                .data(jobDto)
                .build());
    }

}
