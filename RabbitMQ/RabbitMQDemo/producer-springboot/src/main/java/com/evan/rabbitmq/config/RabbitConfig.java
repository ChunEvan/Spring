package com.evan.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    public static final String EXCHANGE_NAME = "boot_topic_exchange";
    public static final String EXCHANGE_NAME2 = "boot_topic_exchange2";
    public static final String QUEUE_NAME = "boot_topic_queue";
    public static final String ROUTE_KEY = "dead_route_key";

    @Bean("bootExchange")
    public Exchange bootExchange(){
        return ExchangeBuilder.topicExchange(EXCHANGE_NAME).durable(true).build();
    }



    @Bean("bootQueue")
    public Queue bootQueue(){
        return QueueBuilder.durable(QUEUE_NAME).build();
    }

    @Bean("bootQueue2")
    public Queue bootQueue2(){
        return QueueBuilder.durable(QUEUE_NAME).deadLetterExchange(EXCHANGE_NAME).deadLetterRoutingKey(ROUTE_KEY).ttl(1000).maxLength(10).build();
    }

    @Bean
    public Binding bindEQueueExchange(@Qualifier("bootExchange") Exchange exchange,@Qualifier("bootQueue") Queue queue){
        return BindingBuilder.bind(queue).to(exchange).with("boot.#").noargs();
    }


    @Bean("bootExchange2")
    public Exchange bootExchange2(){
        return ExchangeBuilder.topicExchange(EXCHANGE_NAME2).durable(true).build();
    }

    @Bean("bootQueue3")
    public Queue bootQueue3(){
        return QueueBuilder.durable(QUEUE_NAME).build();
    }
    @Bean
    public Binding bindEQueueExchange2(@Qualifier("bootExchange2") Exchange exchange,@Qualifier("bootQueue2") Queue queue){
        return BindingBuilder.bind(queue).to(exchange).with(ROUTE_KEY).noargs();
    }
}
