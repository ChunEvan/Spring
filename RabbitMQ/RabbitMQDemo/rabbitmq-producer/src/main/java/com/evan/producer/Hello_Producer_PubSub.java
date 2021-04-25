package com.evan.producer;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Hello_Producer_PubSub {
    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();

        factory.setHost("localhost");
        factory.setPort(5672);
        factory.setVirtualHost("/evan");
        factory.setUsername("evan");
        factory.setPassword("hc123456");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare("test_exchange", BuiltinExchangeType.FANOUT, true, false, false, null);
        channel.queueDeclare("test_queue1", true, false, false, null);
        channel.queueDeclare("test_queue2", true, false, false, null);
        channel.queueBind("test_queue1", "test_exchange", "");
        channel.queueBind("test_queue2", "test_exchange", "");

        channel.queueDeclare("work_queue", true, false, false, null);
        String body = "hello rabbitmq exchange";
        channel.basicPublish("test_exchange", "", null, body.getBytes());
//        channel.close();
//        connection.close();



    }
}
