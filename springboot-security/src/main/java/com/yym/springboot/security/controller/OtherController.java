package com.yym.springboot.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OtherController {
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/hi")
    public String hi(){
        return "hi";
    }
}
