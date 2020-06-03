package com.yym.springboot.aop;

import com.yym.springboot.aop.serivce.Calculator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AopApplication .class)
public class MyTest {



    @Autowired
    private Calculator calculator;


    @Test
    public void test1(){
        System.out.println(calculator);
        System.out.println(calculator.getClass());
        calculator.add(10,11);
        calculator.dev(1,0);
    }

    @Test
    public void test2(){
        ApplicationContext context = new AnnotationConfigApplicationContext();
    }

}
