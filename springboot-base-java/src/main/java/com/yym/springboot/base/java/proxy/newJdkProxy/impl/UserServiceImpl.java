package com.yym.springboot.base.java.proxy.newJdkProxy.impl;

import com.yym.springboot.base.java.proxy.newJdkProxy.UserService;

public class UserServiceImpl implements UserService {
    @Override
    public void sayHello() {
        System.out.println("==== userServiceImpl ==== say hello");
    }
}
