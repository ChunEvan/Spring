package com.evan.producer;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Hello_Producer_Topic {
    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();

        factory.setHost("localhost");
        factory.setPort(5672);
        factory.setVirtualHost("/evan");
        factory.setUsername("evan");
        factory.setPassword("hc123456");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare("topic_test_exchange", BuiltinExchangeType.TOPIC, true, false, false, null);
        channel.queueDeclare("topic_test_queue1", true, false, false, null);
        channel.queueDeclare("topic_test_queue2", true, false, false, null);
        channel.queueBind("topic_test_queue1", "topic_test_exchange", "*.info");
        channel.queueBind("topic_test_queue2", "topic_test_exchange", "error.#");
        channel.queueBind("topic_test_queue2", "topic_test_exchange", "*.*");

        String body = "Hello_Producer_Topic";
        channel.basicPublish("topic_test_exchange", "a.info", null, body.getBytes());
//        channel.close();
//        connection.close();



    }
}
