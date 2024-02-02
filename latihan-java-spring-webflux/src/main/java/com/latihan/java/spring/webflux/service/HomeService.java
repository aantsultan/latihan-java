package com.latihan.java.spring.webflux.service;

import com.latihan.java.spring.webflux.model.InventorySummary;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import reactor.core.publisher.Flux;

import java.util.List;

public interface HomeService {

    Flux<String> getAllData();

    SseEmitter getAllDataSse();

    Flux<ServerSentEvent<String>> getAllDataSseFlux();

}
