package com.latihan.java.spring.webflux.service;

import com.latihan.java.spring.webflux.service.thread.ClosingPeriodAsync;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
@Slf4j
public class ClosingPeriodServiceImpl implements ClosingPeriodService {

    @Value("${sse-emitter.timout.seconds}")
    private String sseEmitterTimeout;

    private final ClosingPeriodAsync async;

    @Autowired
    public ClosingPeriodServiceImpl(ClosingPeriodAsync async) {
        this.async = async;
    }

    @Override
    public SseEmitter closing(String status) {
        SseEmitter emitter = new SseEmitter(Long.parseLong(sseEmitterTimeout) * 1000);
        emitter.onTimeout(emitter::complete);
        async.closingAsync(emitter);
        return emitter;
    }


}
