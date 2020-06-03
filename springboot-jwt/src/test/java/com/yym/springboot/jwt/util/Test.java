package com.yym.springboot.jwt.util;

import com.yym.springboot.jwt.JwtApplication;
import com.yym.springboot.jwt.bean.Person;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JwtApplication.class)
public class Test {

    @Autowired
    private Person person;

    @Value("小花")
    private String catName;

    @Value("#{1*3}")
    private Integer catAge;

    @org.junit.Test
    public void test1(){
        System.out.println("person = " + person);
    }

    @org.junit.Test
    public void test2(){
        System.out.println("catName = " + catName);
        System.out.println("catAge = " + catAge);
    }
}
