package com.yym.springboot.annotation.controller;

import com.yym.springboot.annotation.annotation.ControllerMethodName;
import com.yym.springboot.annotation.service.IUserService;
import com.yym.springboot.common.entity.ResultModel;
import net.bytebuddy.dynamic.scaffold.MethodRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 新增一个注解,用于获取controller的名字
 */

@RestController
public class UserController {

    @Autowired
    private IUserService iUserService;

    @GetMapping("/user/{id}")
    @ControllerMethodName
    public String user(@PathVariable Integer id){
        return ResultModel.success200WithOperation2Json();
    }

    @GetMapping("/time/{times}")
    public String test(@PathVariable Integer times){
        for (int i = 0; i < 4; i++) {
            iUserService.test(times);
        }
        return "success";
    }
}
