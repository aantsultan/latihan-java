package com.latihan.rabbitmq;

import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

@Slf4j
public class ConsumerApplication {

    public static void main(String[] args) throws URISyntaxException, NoSuchAlgorithmException, KeyManagementException, IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqp://guest:guest@localhost:5672");
        factory.setVirtualHost("/");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        DeliverCallback deliverCallback = (consumerTag, message) -> {
            log.info(message.getEnvelope().getRoutingKey());
            log.info(new String(message.getBody()));
        };

        CancelCallback cancelCallback = consumerTag -> {
            log.info("Consumer is cancelled");
        };

        channel.basicConsume("whatsapp", true, deliverCallback, cancelCallback);

    }

}
