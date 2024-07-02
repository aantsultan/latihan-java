package com.latihan.java.activemq.artemis;

import lombok.extern.slf4j.Slf4j;

import javax.jms.*;
import javax.naming.InitialContext;

@Slf4j
public class FirstTopic {

    public static void main(String[] args) throws Exception {

        InitialContext initialContext = new InitialContext();
        Topic topic = (Topic) initialContext.lookup("topic/myTopic");
        ConnectionFactory connectionFactory = (ConnectionFactory) initialContext.lookup("ConnectionFactory");
        Connection connection = connectionFactory.createConnection();
        Session session = connection.createSession();
        MessageProducer messageProducer = session.createProducer(topic);
        MessageConsumer messageConsumer1 = session.createConsumer(topic);
        MessageConsumer messageConsumer2 = session.createConsumer(topic);
        TextMessage messageSent = session.createTextMessage("Hello Topic");
        messageProducer.send(messageSent);
        log.info("Message Sent: {}", messageSent.getText());

        connection.start();

        TextMessage messageReceived1 = (TextMessage) messageConsumer1.receive();
        log.info("Message Received 1: {}", messageReceived1.getText());

        TextMessage messageReceived2 = (TextMessage) messageConsumer2.receive();
        log.info("Message Received 2: {}", messageReceived2.getText());
        connection.close();
        initialContext.close();

    }
}
