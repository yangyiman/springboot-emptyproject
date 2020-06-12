package com.yym.springboot.security.controller;


import com.alibaba.fastjson.JSON;
import com.yym.springboot.security.entity.JdUser;
import com.yym.springboot.security.service.IJdUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yym
 * @since 2020-06-11
 */
@RestController
@RequestMapping("/jd-user")
public class JdUserController {

    @Autowired
    private IJdUserService iJdUserService;

    @GetMapping("/list")
    public String list(){
        List<JdUser> list = iJdUserService.list();
        return JSON.toJSONString(list);
    }

}
