package com.yym.springboot.redis;

import com.yym.springboot.redis.entity.Cat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedisApplication.class)
public class MyTest{

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void test1(){
        Cat cat = new Cat();
        cat.setName("tom");
        cat.setAge(3);
        redisTemplate.opsForValue().set("李","白");
    }
}
