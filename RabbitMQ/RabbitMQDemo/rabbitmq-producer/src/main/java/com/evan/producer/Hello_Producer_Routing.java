package com.evan.producer;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Hello_Producer_Routing {
    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();

        factory.setHost("localhost");
        factory.setPort(5672);
        factory.setVirtualHost("/evan");
        factory.setUsername("evan");
        factory.setPassword("hc123456");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare("direct_test_exchange", BuiltinExchangeType.DIRECT, true, false, false, null);
        channel.queueDeclare("direct_test_queue1", true, false, false, null);
        channel.queueDeclare("direct_test_queue2", true, false, false, null);
        channel.queueBind("direct_test_queue1", "direct_test_exchange", "info");
        channel.queueBind("direct_test_queue1", "direct_test_exchange", "warning");
        channel.queueBind("direct_test_queue2", "direct_test_exchange", "info");
        channel.queueBind("direct_test_queue2", "direct_test_exchange", "error");

        String body = "hello rabbitmq exchange";
        channel.basicPublish("direct_test_exchange", "error", null, body.getBytes());
//        channel.close();
//        connection.close();



    }
}
