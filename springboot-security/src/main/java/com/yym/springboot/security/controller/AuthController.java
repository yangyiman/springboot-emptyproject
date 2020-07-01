package com.yym.springboot.security.controller;

import com.yym.springboot.common.entity.ResultModel;
import com.yym.springboot.security.entity.JdUser;
import com.yym.springboot.security.service.IJdUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        iJdUserService.save(jdUser);
        return ResultModel.success200WithOperation2Json();
    }
}
