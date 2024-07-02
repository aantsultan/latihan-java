package com.latihan.java.logging.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionController implements ErrorController {

    @ExceptionHandler(value = RuntimeException.class)
    public void errorLog(RuntimeException exception) {
        log.error(exception.getMessage(), exception);
    }

}
