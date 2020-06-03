package com.yym.springboot.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class RedisController {

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/put/{key}/{value}")
    public String testPut(@PathVariable String key,@PathVariable String value){
        redisTemplate.opsForValue().set(key,value,1, TimeUnit.HOURS);
        return "存入成功";
    }

    @GetMapping("/get/{key}")
    public String testGet(@PathVariable String key){
        Object o = redisTemplate.opsForValue().get(key);
        if(null == o){
            return "暂无结果,请重试key";
        }
        return o.toString();
    }
}
