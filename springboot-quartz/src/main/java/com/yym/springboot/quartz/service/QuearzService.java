package com.yym.springboot.quartz.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class QuearzService {

    // 每5秒执行一次,星期和日有一个为?,他们是互斥的
    @Scheduled(cron = "2/5 * * * * ?")
    public void printCurrentTime(){
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }
}
