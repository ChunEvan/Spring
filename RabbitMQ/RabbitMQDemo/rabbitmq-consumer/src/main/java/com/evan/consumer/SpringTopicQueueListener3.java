package com.evan.consumer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class SpringTopicQueueListener3 implements MessageListener {
    @Override
    public void onMessage(Message message) {
        System.out.println(message.getBody());
    }
}
