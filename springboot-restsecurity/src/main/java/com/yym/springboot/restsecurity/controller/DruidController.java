package com.yym.springboot.restsecurity.controller;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.alibaba.druid.stat.DruidStatManagerFacade;
import com.yym.springboot.restsecurity.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.util.List;

@RestController
public class DruidController {

    @GetMapping("/druid/stat")
    public Object druid(){
        return DruidStatManagerFacade.getInstance().getDataSourceStatDataList();
    }

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/redis")
    public String redis(){
        RedisUtil redisUtil = new RedisUtil();
        redisUtil.del("leftlist");
        // 输入cba?
        long num = redisUtil.lsetLeft("leftlist", 60L, "a", "b", "c","张三","李四");
        System.out.println("num = " + num);
        List<Object> leftlist = redisUtil.lget("leftlist", 0, -1);
        System.out.println("leftlist = " + leftlist);
        Object v = redisUtil.lpopLeft("leftlist");
        System.out.println("v = " + v);
        leftlist = redisUtil.lget("leftlist", 0, -1);
        System.out.println("leftlist1 = " + leftlist);
        v = redisUtil.lpopRight("leftlist");
        System.out.println("v = " + v);
        leftlist = redisUtil.lget("leftlist", 0, -1);
        System.out.println("leftlist2 = " + leftlist);
        return "redis test";
    }
}
