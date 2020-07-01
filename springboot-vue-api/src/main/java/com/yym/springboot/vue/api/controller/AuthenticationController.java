package com.yym.springboot.vue.api.controller;

import com.yym.springboot.common.entity.ResultModel;
import com.yym.springboot.vue.api.entity.JdUser;
import com.yym.springboot.vue.api.service.IJdUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationController {

    @Autowired
    private IJdUserService iJdUserService;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @PostMapping("/auth/login")
    public String login(@RequestBody JdUser jdUser){
        JdUser jdUserInDB = iJdUserService.login(jdUser);
        return ResultModel.success200WithOperation2Json(jdUserInDB);
    }

    /*@GetMapping("/{name}")
    public String getName(@PathVariable String name){
        jdbcTemplate.execute("select password from jd_user where username = "+name);
        //jdbcTemplate.e
    }*/
}
