package com.latihan.java.spring.webflux.helper;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class GenericEvent <T> extends ApplicationEvent {

    private T message;
    public GenericEvent(Object source, T message) {
        super(source);
        this.message = message;
    }

}
