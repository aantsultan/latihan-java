package com.latihan.java.springdasar.controller;

import com.common.util.specific.ControllerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;

@RestController
public class LinkedHashMapController {

    @Autowired
    ControllerUtil util;

    @PostMapping("/linkedlist")
    public ResponseEntity save(@RequestBody LinkedHashMap<String, String> request) {
        if(!util.iso8583(request)){
            return new ResponseEntity("ERROR KEY ORDER IN JSON", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity("SUCCESS", HttpStatus.OK);
    }
}
