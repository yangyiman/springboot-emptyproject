package com.yym.springboot.rabbitmq.direct;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 测试直连模式:不需要指定routinKey和绑定exchage,直接按队列名称添加即可
 */
@Component
public class DirectProduct {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void product(){
        rabbitTemplate.convertAndSend("direct","测试direct模式");
    }

}
