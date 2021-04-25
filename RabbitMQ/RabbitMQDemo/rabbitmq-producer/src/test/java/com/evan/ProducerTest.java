package com.evan;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-rabbitmq-producer.xml")
public class ProducerTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void test(){
        rabbitTemplate.convertAndSend("spring_queue", "123");
    }

    @Test
    public void testFanout(){
        rabbitTemplate.convertAndSend("spring_fanout_exchange", "","hello spring_fanout_exchange");
    }

    @Test
    public void testTopic(){
        rabbitTemplate.convertAndSend("spring_topic_exchange", "it.evan.evan","hello spring_topic_exchange");
    }

    @Test
    public void testCallback() throws InterruptedException {

        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setReturnsCallback(new RabbitTemplate.ReturnsCallback() {
            @Override
            public void returnedMessage(ReturnedMessage returnedMessage) {
                System.out.println("setReturnsCallback");
                System.out.println(returnedMessage.getExchange());
                System.out.println(returnedMessage.getMessage());
                System.out.println(returnedMessage.getReplyCode());
                System.out.println(returnedMessage.getReplyText());
                System.out.println(returnedMessage.getRoutingKey());
            }
        });
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            System.out.println("setConfirmCallback");
            System.out.println(correlationData);
            if (ack){
                System.out.println("success "+ cause);
            }else {
                System.out.println("failed "+ cause);
            }

        });
        for (int i=0;i<10;i++){
            rabbitTemplate.convertAndSend("spring_direct_exchange", "info", "hello confirm call back");

        }
    }

    @Test
    public void testTTl(){
        MessagePostProcessor messagePostProcessor = new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setExpiration("5000");
                return message;
            }
        };

        for (int i=0;i<10;i++){
            if (i==5){
                rabbitTemplate.convertAndSend("test_exchange_ttl", "ttl", "hello ttl", messagePostProcessor);
            }else {
                rabbitTemplate.convertAndSend("test_exchange_ttl", "ttl", "hello ttl");
            }
        }
    }

    @Test
    public void testDead(){
        rabbitTemplate.convertAndSend("test_exchange_dlx", "test_dlx.hh", "hello dead");

//        for (int i=0;i<20;i++){
//            rabbitTemplate.convertAndSend("test_exchange_dlx", "test_dlx.hh", "hello dead");
//        }
    }
}
