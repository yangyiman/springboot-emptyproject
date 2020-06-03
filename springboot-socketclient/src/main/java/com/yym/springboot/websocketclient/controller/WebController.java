package com.yym.springboot.websocketclient.controller;

import org.java_websocket.client.WebSocketClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

    @Autowired
    private WebSocketClient webSocketClient;

    @GetMapping("/hello")
    public String sayHello(){
        webSocketClient.send("hello");
        return "hello";
    }
}
