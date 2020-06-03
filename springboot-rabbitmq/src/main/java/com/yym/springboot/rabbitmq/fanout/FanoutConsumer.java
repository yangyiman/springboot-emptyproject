package com.yym.springboot.rabbitmq.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = {"fanout-1","fanout-2"})
public class FanoutConsumer {

    @RabbitHandler
    public void consumer(String msg){
        System.out.println("fanout收到消息: "+msg);
    }

}
