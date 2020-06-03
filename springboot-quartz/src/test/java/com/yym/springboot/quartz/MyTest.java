package com.yym.springboot.quartz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = QuartzApplication.class)
public class MyTest{

    @Test
    public void test1(){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime endTime = LocalDateTime.of(2020, 10, 22, 10, 10, 10);
        boolean after = now.isAfter(endTime);
        System.out.println("after = " + after);
    }
}
