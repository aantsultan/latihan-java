package com.latihan.rabbitmq;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ProducerApplication {

    private static final String URI = "amqp://guest:guest@localhost:5672";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri(URI);
        factory.setVirtualHost("/");

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel();) {
            for (int i = 0; i < 10; i++) {
                String message = "Whatsapp " + i;

                Map<String, Object> sample = new HashMap<>();
                sample.put("sample", "value");

                AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                        .headers(sample)
                        .build();

                channel.basicPublish("notification", "whatsapp", properties, message.getBytes());
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

    }

}
