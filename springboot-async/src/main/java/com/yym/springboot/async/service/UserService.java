package com.yym.springboot.async.service;

import com.yym.springboot.async.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {
    // 邮件任务
    @Autowired
    EmailService emailService;

    // 新增用户
    public String addUser( User user) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        emailService.sendEmail(user);
        long endTime = System.currentTimeMillis();
        log.info("注册耗时：" + (endTime - startTime));
        return user.toString();
    }
}
