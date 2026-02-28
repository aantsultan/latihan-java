package com.latihan.java.multiple.database.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdviceExceptionController {

    @ExceptionHandler(TransactionException.class)
    public ResponseEntity<String> transactionFailed(TransactionException e) {
        return ResponseEntity.internalServerError().body(e.getMessage());
    }

}
