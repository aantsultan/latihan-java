package com.latihan.java.activemq.artemis;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@Slf4j
public class JMSContextDemo {

    // JMS Context = Connection + Session

    public static void main(String[] args) throws NamingException {

        InitialContext context = new InitialContext();
        Queue queue = (Queue) context.lookup("queue/myQueue");

        try (ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
             JMSContext jmsContext = cf.createContext()) {

            jmsContext.createProducer().send(queue, "Hello JMS Context");

            String messageReceived = jmsContext.createConsumer(queue).receiveBody(String.class);
            log.info("Message Received: {}", messageReceived);
        }

    }
}
