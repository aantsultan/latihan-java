package com.latihan.java.spring.bc365.controller;

import com.latihan.java.spring.bc365.dto.ODataV4Dto;
import com.latihan.java.spring.bc365.dto.WebResponse;
import com.latihan.java.spring.bc365.dto.sampleuserlist.CreateSULDto;
import com.latihan.java.spring.bc365.dto.sampleuserlist.SampleUserListDto;
import com.latihan.java.spring.bc365.dto.sampleuserlist.UpdateSULDto;
import com.latihan.java.spring.bc365.service.SampleUserListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/api/sample-user-list/{uid}")
    public ResponseEntity<WebResponse<SampleUserListDto>> getByUid(@PathVariable("uid") Integer uid) {
        SampleUserListDto result = service.findByUid(uid);
        return ResponseEntity.ok(WebResponse.<SampleUserListDto>builder().data(result).build());
    }

    @PostMapping("/api/sample-user-list")
    public ResponseEntity<WebResponse<String>> save(@RequestBody CreateSULDto request) {
        service.save(request);
        return new ResponseEntity<>(WebResponse.<String>builder().data("OK").build(), HttpStatus.CREATED);
    }

    @PatchMapping("/api/sample-user-list/{uid}")
    public ResponseEntity<WebResponse<String>> update(@RequestBody UpdateSULDto request, @PathVariable Integer uid) {
        service.update(request, uid);
        return new ResponseEntity<>(WebResponse.<String>builder().data("OK").build(), HttpStatus.OK);
    }

    @DeleteMapping("/api/sample-user-list/{uid}")
    public ResponseEntity<WebResponse<String>> delete(@PathVariable Integer uid) {
        service.deleteByUid(uid);
        return new ResponseEntity<>(WebResponse.<String>builder().data("OK").build(), HttpStatus.NO_CONTENT);
    }


}
