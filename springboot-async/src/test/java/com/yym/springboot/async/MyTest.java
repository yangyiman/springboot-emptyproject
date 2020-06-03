package com.yym.springboot.async;

import com.yym.springboot.async.entity.User;
import org.junit.Test;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

public class MyTest {

    @Test
    public void test1(){
        User tom = new User().setName("tom").setAge(20);
        List<User> list = Arrays.asList(new User().setName("tom").setAge(20),tom);
        // 打印出名字
        list.stream().map(User::getName).forEach(System.out::println);
    }

    @Test
    public void test2(){
        testNotNull(new User());
    }
    public void testNotNull(@Valid  User user){
        System.out.println("user = " + user);
    }
}
