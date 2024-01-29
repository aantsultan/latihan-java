package com.latihan.java.internationalization.controller;

import com.latihan.java.internationalization.service.LatihanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
public class LatihanController {

    @Autowired
    private LatihanService service;

    @PostMapping(value = "/latihan", consumes = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> latihan(
            @RequestHeader(HttpHeaders.CONTENT_LANGUAGE) Locale language,
            @RequestBody String name
    ){
        return service.latihan(name, language);
    }

}
