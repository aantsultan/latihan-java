package consumer.app.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Service
@Slf4j
public class ConsumeMessageServiceImpl implements ConsumeMessageService {

    @Value("${spring.webflux.duration}")
    private String webfluxDuration;

    private final MessageListenerContainer listener;

    @Autowired
    public ConsumeMessageServiceImpl(@Qualifier("createMessageListener") MessageListenerContainer listener) {
        this.listener = listener;
    }

    @Override
    public Flux<String> consume() {
        Flux<String> result = Flux.create(data -> {
            listener.setupMessageListener(message -> {
                String payload = new String(message.getBody());
                data.next(payload);
            });
            data.onRequest(request -> listener.start());
            data.onDispose(listener::stop);
        });

        return Flux.interval(Duration.ofSeconds(Long.parseLong(webfluxDuration)))
                .map(data -> "No email received")
                .mergeWith(result);
    }
}
