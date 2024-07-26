package com.latihan.java.spring.bc365.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobController {

    @GetMapping("/api/jobs")
    public String get(){
        return "Hello Jobs";
    }

}
