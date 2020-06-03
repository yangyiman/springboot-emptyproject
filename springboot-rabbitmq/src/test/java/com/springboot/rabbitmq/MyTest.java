package com.springboot.rabbitmq;

import com.yym.springboot.rabbitmq.RabbitmqApplication;
import com.yym.springboot.rabbitmq.direct.DirectProduct;
import com.yym.springboot.rabbitmq.fanout.FanoutProduct;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RabbitmqApplication.class)
public class MyTest {

    @Autowired
    private FanoutProduct fanoutProduct;

    @Autowired
    private DirectProduct directProduct;

    @Test
    public void testFanout(){
        fanoutProduct.product();
        System.out.println("fanout发送成功");
    }

    @Test
    public void testDirect(){
        directProduct.product();
        System.out.println("product发送成功");
    }
}
