package consumer.app.config;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.queue-names}")
    private String rabbitMQQueueNames;

    private final ConnectionFactory factory;

    @Autowired
    public RabbitMQConfig(ConnectionFactory factory) {
        this.factory = factory;
    }

    @Bean("createMessageListener")
    public MessageListenerContainer create() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(factory);
        container.setQueueNames(rabbitMQQueueNames);
        return container;
    }

}
