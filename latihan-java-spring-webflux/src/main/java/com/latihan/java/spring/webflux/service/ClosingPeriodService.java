package com.latihan.java.spring.webflux.service;

import com.latihan.java.spring.webflux.dto.ClosingPeriodDto;
import com.latihan.java.spring.webflux.dto.SseMessageDto;
import com.latihan.java.spring.webflux.helper.GenericEvent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface ClosingPeriodService {
    SseEmitter closing(String status);

    SseEmitter closeStream();

    void closing() throws InterruptedException;

    ResponseEntity<String> postClose(ClosingPeriodDto closingPeriodDto);

    void closingPeriodListener(GenericEvent<SseMessageDto> message);
}
