package consumer.app.controller;

import consumer.app.service.ConsumeMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Flux;

@Controller
public class HomeController {

    private final ConsumeMessageService service;

    @Autowired
    public HomeController(ConsumeMessageService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String view() {
        return "index";
    }

    @GetMapping(value = "/consume", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> consume() {
        return service.consume();
    }

}
