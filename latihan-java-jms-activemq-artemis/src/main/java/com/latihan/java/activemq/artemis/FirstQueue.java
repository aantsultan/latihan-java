package com.latihan.java.activemq.artemis;

import lombok.extern.slf4j.Slf4j;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@Slf4j
public class FirstQueue {

    public static void main(String[] args) {

        InitialContext initialContext = null;
        Connection connection = null;
        try {
            initialContext = new InitialContext();
            ConnectionFactory connectionFactory = (ConnectionFactory) initialContext.lookup("ConnectionFactory");
            connection = connectionFactory.createConnection();
            Session session = connection.createSession();
            Queue queue = (Queue) initialContext.lookup("queue/myQueue");
            MessageProducer messageProducer = session.createProducer(queue);
            TextMessage messageSent = session.createTextMessage("Hello World");
            messageProducer.send(messageSent);
            log.info("Message Sent: {}", messageSent.getText());

            MessageConsumer messageConsumer = session.createConsumer(queue);
            connection.start();
            TextMessage messageReceived = (TextMessage) messageConsumer.receive(5000);
            log.info("Message Received: {}", messageReceived.getText());
        } catch (NamingException | JMSException ne) {
            log.error(ne.getMessage(), ne);
        } finally {
            if (initialContext != null) {
                try {
                    initialContext.close();
                } catch (NamingException e) {
                    log.error(e.getMessage(), e);
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
    }

}
