package com.latihan.java.spring.webflux.controller;

import com.latihan.java.spring.webflux.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import reactor.core.publisher.Flux;

@Controller
public class HomeController {

    private final HomeService service;

    @Autowired
    public HomeController(HomeService service) {
        this.service = service;
    }


    @GetMapping
    public String home() {
        return "index";
    }

    @GetMapping(value = "/flux", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> flux() {
        return service.getAllData();
    }

    @GetMapping(value = "/stream-sse-mvc", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter streamSseMvc() {
        return service.getAllDataSse();
    }

}
