package consumer.app.service;

import reactor.core.publisher.Flux;

public interface ConsumeMessageService {

    Flux<String> consume();

}
