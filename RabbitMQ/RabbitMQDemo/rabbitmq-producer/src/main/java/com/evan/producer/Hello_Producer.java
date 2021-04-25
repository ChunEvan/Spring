package com.evan.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Hello_Producer {
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
        String body = "hello rabbitmq";
        channel.basicPublish("", "hello", null, body.getBytes());
        channel.close();
        connection.close();



    }
}
