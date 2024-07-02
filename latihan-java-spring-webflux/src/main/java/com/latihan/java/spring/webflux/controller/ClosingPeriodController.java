package com.latihan.java.spring.webflux.controller;

import com.latihan.java.spring.webflux.dto.ClosingPeriodDto;
import com.latihan.java.spring.webflux.service.ClosingPeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Controller
@RequestMapping("/closing-period")
public class ClosingPeriodController {

    private final ClosingPeriodService service;

    @Autowired
    public ClosingPeriodController(ClosingPeriodService service) {
        this.service = service;
    }

    @GetMapping
    public String search() {
        return "closing-period/search";
    }

    @GetMapping("/close/{status}")
    public SseEmitter close(@PathVariable("status") String status) {
        return service.closing(status);
    }

    @GetMapping(value = "/close", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter closeStream() {
        return service.closeStream();
    }

    @PostMapping(value = "/close", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postClose(@RequestBody ClosingPeriodDto closingPeriodDto) {
        return service.postClose(closingPeriodDto);
    }
}
