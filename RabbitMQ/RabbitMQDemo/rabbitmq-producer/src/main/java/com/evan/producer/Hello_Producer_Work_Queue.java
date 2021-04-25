package com.evan.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Hello_Producer_Work_Queue {
    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();

        factory.setHost("localhost");
        factory.setPort(5672);
        factory.setVirtualHost("/evan");
        factory.setUsername("evan");
        factory.setPassword("hc123456");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("work_queue", true, false, false, null);
        ;
        for (int i=1;i<=10;i++){
            String body = "hello rabbitmq "+i;
            channel.basicPublish("", "work_queue", null, body.getBytes());
        }
        channel.close();
        connection.close();



    }
}
