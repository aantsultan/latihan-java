package com.latihan.java.spring.webflux.service;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface ClosingPeriodService {
    SseEmitter closing(String status);

}
