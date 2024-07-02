package com.latihan.java.spring.webflux.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.latihan.java.spring.webflux.constant.SSEConstant;
import com.latihan.java.spring.webflux.dto.ClosingPeriodDto;
import com.latihan.java.spring.webflux.dto.SseMessageDto;
import com.latihan.java.spring.webflux.helper.GenericEvent;
import com.latihan.java.spring.webflux.service.thread.ClosingPeriodAsync;
import com.latihan.java.spring.webflux.service.thread.ClosingPeriodAsyncStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@Service
@Slf4j
public class ClosingPeriodServiceImpl implements ClosingPeriodService {

    @Value("${sse-emitter.timout.seconds}")
    private String sseEmitterTimeout;

    private final ClosingPeriodAsync async;
    private final ObjectMapper mapper;
    private final ClosingPeriodAsyncStream asyncStream;
    private final Set<SseEmitter> clients = new CopyOnWriteArraySet<>();

    @Autowired
    public ClosingPeriodServiceImpl(ClosingPeriodAsync async, ObjectMapper mapper
            , ClosingPeriodAsyncStream asyncStream) {
        this.async = async;
        this.mapper = mapper;
        this.asyncStream = asyncStream;
    }

    @Override
    public SseEmitter closing(String status) {
        SseEmitter emitter = new SseEmitter(Long.parseLong(sseEmitterTimeout) * 1000);
        emitter.onTimeout(emitter::complete);
        async.closingAsync(emitter);
        return emitter;
    }

    @Override
    public SseEmitter closeStream() {
        SseEmitter emitter = new SseEmitter(Long.parseLong(sseEmitterTimeout) * 1000);
        this.clients.add(emitter);
        emitter.onTimeout(() -> this.clients.remove(emitter));
        emitter.onError(throwable -> this.clients.remove(emitter));
        return emitter;
    }

    @Override
    public void closing() throws InterruptedException {
        asyncStream.closingAsync();
    }

    @Override
    public ResponseEntity<String> postClose(ClosingPeriodDto closingPeriodDto) {
        try {
            log.info("Post Close is start");
            this.closing();
            return ResponseEntity.ok().body("SUCCESS");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @EventListener
    @Override
    public void closingPeriodListener(GenericEvent<SseMessageDto> message) {
        List<SseEmitter> errorEmitters = new ArrayList<>();
        this.clients.forEach(emitter -> {
            try {
                SseEmitter.SseEventBuilder event = SseEmitter.event()
                        .data(mapper.writeValueAsString(message))
                        .id(String.valueOf(message.getMessage().getCurrentData()))
                        .name(SSEConstant.SSE_NAME_CLOSING_PERIOD);
                log.info("event listener {} {}", message.getMessage().getType(), message.getMessage().getCurrentData());
                emitter.send(event);
            } catch (Exception e) {
                errorEmitters.add(emitter);
                emitter.completeWithError(e);
            }
        });
        errorEmitters.forEach(this.clients::remove);
    }


}
