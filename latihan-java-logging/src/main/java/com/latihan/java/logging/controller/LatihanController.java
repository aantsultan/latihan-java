package com.latihan.java.logging.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class LatihanController {

    @PostMapping(value = "/latihan", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> latihan(@RequestBody String input) {
        log.info("REQUEST latihan : {}", input);
        log.info("RESPONSE latihan : {}", input);
        return new ResponseEntity<>(input, HttpStatus.OK);
    }

    private void error() {
        throw new RuntimeException("Method is failed");
    }


}
