package com.latihan.java.spring.soap.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/api/users")
    public ResponseEntity<String> get() {
        return ResponseEntity.ok("Hello User");
    }

}
