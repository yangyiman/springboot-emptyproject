package com.yym.springboot.async.controller;

import com.yym.springboot.async.entity.User;
import com.yym.springboot.async.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/add")
    public String user(@Valid User user, BindingResult result) throws InterruptedException {
        if (result.hasErrors()) {
            FieldError error = result.getFieldError();
            return error.getField() + error.getDefaultMessage();
        }
        return userService.addUser(user);
    }
}
