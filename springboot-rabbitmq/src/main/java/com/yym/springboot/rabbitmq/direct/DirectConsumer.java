package com.yym.springboot.rabbitmq.direct;

import org.springframework.stereotype.Component;

/**
 * 用户
 */
@Component
//@RabbitListener(queues = "direct-queue")
public class DirectConsumer {

    //@RabbitHandler
    public void consumer(String msg){
        System.out.println("msg = " + msg);
    }
}
