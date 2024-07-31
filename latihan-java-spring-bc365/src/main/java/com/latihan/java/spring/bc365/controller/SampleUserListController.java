package com.latihan.java.spring.bc365.controller;

import com.latihan.java.spring.bc365.dto.ODataV4Dto;
import com.latihan.java.spring.bc365.dto.SampleUserListDto;
import com.latihan.java.spring.bc365.dto.WebResponse;
import com.latihan.java.spring.bc365.service.SampleUserListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SampleUserListController {

    private final SampleUserListService service;

    @GetMapping("/api/sample-user-list")
    public ResponseEntity<WebResponse<ODataV4Dto<SampleUserListDto>>> get() {
        ODataV4Dto<SampleUserListDto> result = service.findAll();
        return ResponseEntity.ok(WebResponse.<ODataV4Dto<SampleUserListDto>>builder()
                .data(result)
                .build());
    }

    @PostMapping("/api/sample-user-list")
    public ResponseEntity<WebResponse<String>> save(@RequestBody SampleUserListDto request) {
        service.save(request);
        return new ResponseEntity<>(WebResponse.<String>builder().data("OK").build(), HttpStatus.CREATED);
    }

}
