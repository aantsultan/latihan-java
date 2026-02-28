package com.latihan.java.multiple.database.controller;

import com.latihan.java.multiple.database.dto.md.SalesGroupDto;
import com.latihan.java.multiple.database.service.SalesGroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SalesGroupController {

    private final SalesGroupService service;

    public SalesGroupController(SalesGroupService service) {
        this.service = service;
    }

    @GetMapping(value = "/api/salesgroups")
    public ResponseEntity<List<SalesGroupDto>> get() {
        List<SalesGroupDto> dtos = service.findAll();
        return ResponseEntity.ok(dtos);
    }

}
