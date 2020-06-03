package com.yym.springboot.base.java.file;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 测试properties文件
 */
@Slf4j
public class PropertiesTest {

    // new,runnable,blocked,waiting,timed_waiting,terminated
    @Test
    public void test() throws IOException {
        Properties pro = new Properties();
        pro.load(new FileInputStream("db.properties"));
        String user = pro.getProperty("user");
        log.info(user);
        String password = pro.getProperty("password");
        log.info(password);
    }

    /**
     * list的add方法:
     *  ReentrantLock 加锁,
     *  将原来数组扩容加1,将新加的元素放置尾部
     */
    @Test
    public void test2(){
        List<Integer> list = new CopyOnWriteArrayList<>();
        // HashSet的底层原理就是HashMap,但是value的值就是它其中的一个常量
        Set<String> set = new HashSet<>();
    }
}
