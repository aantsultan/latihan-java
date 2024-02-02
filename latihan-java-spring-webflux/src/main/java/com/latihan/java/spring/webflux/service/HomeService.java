package com.latihan.java.spring.webflux.service;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import reactor.core.publisher.Flux;

public interface HomeService {

    Flux<String> getAllData();

    SseEmitter getAllDataSse();

}
