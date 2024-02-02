package com.latihan.java.spring.webflux.service;

import com.latihan.java.spring.webflux.constant.SSEConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@Slf4j
public class HomeServiceImpl implements HomeService {

    @Value("${spring.webflux.duration}")
    private String webFluxDuration;

    @Override
    public Flux<String> getAllData() {
        List<String> input = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            input.add("Hello " + i);
        }

        Flux<String> result = Flux.fromIterable(input);
        return Flux.interval(Duration.ofSeconds(Long.parseLong(webFluxDuration)))
                .map(data -> "No data received")
                .mergeWith(result);
    }

    @Override
    public SseEmitter getAllDataSse() {
        SseEmitter emitter = new SseEmitter();
        ExecutorService sseMvcExecutor = Executors.newSingleThreadExecutor();
        sseMvcExecutor.execute(() -> {
            try {
                log.info("getAllDataSse start :");
                for (int i = 0; i < 10; i++) {
                    SseEmitter.SseEventBuilder event = SseEmitter.event()
                            .data("SSE MVC - " + LocalTime.now())
                            .id(String.valueOf(i))
                            .name(SSEConstant.SSE_NAME);
                    emitter.send(event);
                    Thread.sleep(1000);
                }
                emitter.send(SseEmitter.event().name(SSEConstant.SSE_NAME).data(SSEConstant.CLOSE));
                log.info("getAllDataSse finished");
            } catch (Exception ex) {
                emitter.completeWithError(ex);
            }
        });
        return emitter;
    }

    @Override
    public Flux<ServerSentEvent<String>> getAllDataSseFlux() {
        // process is running
        Flux<ServerSentEvent<String>> result = Flux.interval(Duration.ofSeconds(1))
                .map(data -> ServerSentEvent.<String>builder()
                        .id(String.valueOf(data))
                        .event(SSEConstant.SSE_NAME)
                        .data("SSE - " + LocalTime.now())
                        .build());

        // if process is done
        result = result.map(data -> ServerSentEvent.<String>builder()
                .id(String.valueOf(data))
                .event(SSEConstant.SSE_NAME)
                .data(SSEConstant.CLOSE)
                .build());

        return result;
    }
}
