package com.yym.springboot.security.controller;

import com.alibaba.fastjson.JSON;
import com.yym.springboot.security.entity.JdUser;
import com.yym.springboot.security.service.IJdUserService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private IJdUserService iJdUserService;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @PostMapping("/register")
    public String addUser(@RequestBody JdUser jdUser){
        jdUser.setPassword(encoder.encode(jdUser.getPassword()));
        boolean save = iJdUserService.save(jdUser);
        Map<String, Object> map = new HashMap<>();
        map.put("status", 200);
        map.put("msg", "插入成功");
        map.put("data",jdUser);
        return JSON.toJSONString(map);
    }

    @GetMapping("/test")
    public String test(){
        return "试试水";
    }
}
