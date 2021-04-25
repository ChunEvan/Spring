package com.evan.consumer;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Hello_Consumer {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(5672);
        factory.setVirtualHost("/evan");
        factory.setUsername("evan");
        factory.setPassword("hc123456");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("hello", true, false, false, null);
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("consumerTag :"+consumerTag);
                System.out.println("exchange :"+ envelope.getExchange());
                System.out.println("RoutingKey :"+ envelope.getRoutingKey());
                System.out.println("properties :"+ properties);
                System.out.println("body :"+new String(body));
                System.out.println("=========================");
            }
        };
        channel.basicConsume("hello", true, consumer);
    }
}
