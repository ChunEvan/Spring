package com.evan.consumerspringboot;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitmqListener {

    @RabbitListener(queues = "boot_topic_queue")
    public void listenerQueue(Message message){
        System.out.println(message);
        System.out.println(new String(message.getBody()));
    }
}
