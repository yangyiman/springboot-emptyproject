package com.yym.springboot.validated.controller;

import com.yym.springboot.common.entity.ResultModel;
import com.yym.springboot.common.exception.CommonException;
import com.yym.springboot.validated.entity.User;
import com.yym.springboot.validated.service.ValidatedService;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@Validated
public class ValidateController {

    @Autowired
    private ValidatedService validatedService;

    @PostMapping("/add")
    public String insertUser(@Valid @RequestBody User user) {
        return ResultModel.success200WithOperation2Json(user);
    }

    @GetMapping("/user")
    public String insertUser2(@Valid User user) {
        return ResultModel.success200WithOperation2Json(user);
    }

    @GetMapping("/addNoParam")
    public String insertUser3() {
        User user = new User();
        boolean isAdd = validatedService.add3(user);
        return ResultModel.success200WithOperation2Json(user);
    }

    @GetMapping("/get")
    public String insertUser(@RequestParam String userName) throws CommonException {
        if (StringUtils.isBlank(userName)) {
            throw new CommonException(400, "姓名错误");
        }
        return userName;
    }

    @GetMapping("/age")
    public String insertAge(@Range(min = 0,max = 20,message = "年龄介于0到20岁") @RequestParam Integer age) {
        return "nianling = " + age.toString();
    }

}
