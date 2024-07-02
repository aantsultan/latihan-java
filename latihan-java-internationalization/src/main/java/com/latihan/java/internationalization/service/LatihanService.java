package com.latihan.java.internationalization.service;

import org.springframework.http.ResponseEntity;

import java.util.Locale;

public interface LatihanService {
    ResponseEntity<String> latihan(String name, Locale language);
}
