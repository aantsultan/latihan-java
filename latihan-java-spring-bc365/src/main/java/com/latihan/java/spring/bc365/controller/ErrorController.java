package com.latihan.java.spring.bc365.controller;

import com.latihan.java.spring.bc365.dto.WebResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<WebResponse<String>> response(ResponseStatusException exception) {
        return ResponseEntity.status(exception.getRawStatusCode())
                .body(WebResponse.<String>builder()
                        .errors(exception.getMessage())
                        .build());
    }

}
