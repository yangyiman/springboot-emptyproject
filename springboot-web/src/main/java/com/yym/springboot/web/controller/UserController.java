package com.yym.springboot.web.controller;

import com.alibaba.fastjson.JSON;
import com.yym.springboot.common.entity.ResultModel;
import com.yym.springboot.web.annotation.ListParam;
import com.yym.springboot.web.entity.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import java.util.List;

@RestController
public class UserController {

    @PostMapping("/user")
    public String getUser(@RequestBody User user){
        System.out.println("user = " + user);
        return ResultModel.success200WithOperation2Json(user);
    }

    @PostMapping("/user1")
    public String getUser1(@RequestBody User user){
        System.out.println("user = " + user);
        return ResultModel.success200WithOperation2Json(user);
    }

    /**
     * 接受单个参数可以将逗号转换为array或者list
     *
     * 如果不使用@RequestParam,默认逗号分隔的专为array
     * 如果需要专为集合,需要加上注解
     * @param desc
     * @return
     */
    @GetMapping("/user2")
    public String getUser2(@RequestParam List<Integer> desc){
        return ResultModel.success200WithOperation2Json(desc);
    }

    /**
     * 传逗号分隔字符串,报错
     * @param desc
     * @return
     */
    @GetMapping("/user3")
    public String getUser3(List<Integer> desc){
        return ResultModel.success200WithOperation2Json(desc);
    }

    @GetMapping("/user4")
    public String getUser4(Integer[] desc){
        return ResultModel.success200WithOperation2Json(desc);
    }

    @GetMapping("/user5")
    public String getUser5(@RequestParam Integer[] desc){
        return ResultModel.success200WithOperation2Json(desc);
    }
}
