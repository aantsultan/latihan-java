package com.latihan.java.spring.webflux.service.event;

import com.latihan.java.spring.webflux.dto.SseMessageDto;
import com.latihan.java.spring.webflux.helper.GenericEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PublishEventService {

    private final ApplicationEventPublisher publisher;

    @Autowired
    public PublishEventService(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void publish(final SseMessageDto message){
        GenericEvent<SseMessageDto> event = new GenericEvent<>(this, message);
        publisher.publishEvent(event);
    }
}
