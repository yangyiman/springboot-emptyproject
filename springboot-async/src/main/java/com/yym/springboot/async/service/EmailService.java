package com.yym.springboot.async.service;

import com.yym.springboot.async.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService {

    @Async
    public void sendEmail(User user) throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread.sleep(5000);
        long end = System.currentTimeMillis();
        log.info("发送激活邮箱成功！" + (end - start) + "ms");
    }
}
