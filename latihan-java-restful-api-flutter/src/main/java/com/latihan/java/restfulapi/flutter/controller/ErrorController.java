package com.latihan.java.restfulapi.flutter.controller;

import com.latihan.java.restfulapi.flutter.dto.response.WebResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
@Slf4j
public class ErrorController {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<WebResponse<String>> constraintViolation(ConstraintViolationException exception) {
        log.error(exception.getMessage(), exception);
        return ResponseEntity.badRequest().body(
                WebResponse.<String>builder()
                        .errors(exception.getMessage())
                        .build());
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<WebResponse<String>> responseStatus(ResponseStatusException exception) {
        log.error(exception.getMessage(), exception);
        return new ResponseEntity<>(WebResponse.<String>builder()
                .errors(exception.getMessage())
                .build(),
                exception.getStatus());
    }
}
