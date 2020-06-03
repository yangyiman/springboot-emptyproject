package com.yym.springboot.rabbitmq.fanout;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FanoutProduct {

    @Autowired
    RabbitTemplate rabbitTemplate;

    public void product(){
        rabbitTemplate.convertAndSend("fanout-exchange","","fanout-hello");
    }
}
