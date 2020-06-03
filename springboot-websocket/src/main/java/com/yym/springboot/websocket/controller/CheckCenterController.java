package com.yym.springboot.websocket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CheckCenterController {

    @GetMapping("/copy/{index}")
    public String page(@PathVariable String index){
        return "copy"+index;
    }

    @GetMapping("/hello")
    public String page1(){
        return "hello";
    }

    @GetMapping("/page/{index}")
    public String page2(@PathVariable String index){
        return "js-test"+index;
    }
}

