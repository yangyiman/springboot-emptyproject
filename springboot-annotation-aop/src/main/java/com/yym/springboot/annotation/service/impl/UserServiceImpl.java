package com.yym.springboot.annotation.service.impl;

import com.yym.springboot.annotation.annotation.ControllerMethodName;
import com.yym.springboot.annotation.service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @ControllerMethodName
    @Override
    public String test(Integer times) {
        StringBuilder builder = new StringBuilder();
        if(times > 20){
            times = 20;
        }
        for (int i = 0; i < times; i++) {
            builder.append("success---i");
            builder.append("\r\n");
        }
        return builder.toString();
    }
}
