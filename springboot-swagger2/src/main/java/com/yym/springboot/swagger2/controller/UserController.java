package com.yym.springboot.swagger2.controller;

import com.alibaba.fastjson.JSON;
import com.yym.springboot.autoconfig.bean.Cat;
import com.yym.springboot.common.entity.ResultModel;
import com.yym.springboot.swagger2.serivce.RegistryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Api(tags = "用户相关接口")
public class UserController {

    @Autowired(required = false)
    private Cat cat;
    @Autowired
    ApplicationContext applicationContext;

    @GetMapping("/user")
    @ApiOperation("查询用户接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", defaultValue = "李四"),
            @ApiImplicitParam(name = "password", value = "用户密码", defaultValue = "123123", required = true)
    })
    public String getOneUser(@RequestParam(value = "username",required = false) String username,
                             @RequestParam(value = "password")String password ){
        Map<String, Object> user = new HashMap<>();
        user.put("name", "tom");
        user.put("gender", "男");
        user.put("age", 30);
        return JSON.toJSONString(user);
    }

    @GetMapping("/cat")
    public String cat(){
        return ResultModel.success200WithOperation2Json(cat);
    }

    @GetMapping("/listBean")
    public String list(){
        return ResultModel.success200WithOperation2Json(applicationContext.getBeanDefinitionNames());
    }

    @Autowired
    private RegistryService registryService;
    @GetMapping("/reg")
    public String reg(){
        int haha = registryService.getAge("haha");
        return String.valueOf(haha);
    }

}
