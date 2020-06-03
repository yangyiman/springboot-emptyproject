package com.yym.springboot.base.java;

import com.yym.springboot.base.java.entity.Student;
import org.junit.Test;

public class testMath {

    /**
     * 测试Math.ceil方法取大于浮点型的整数
     */
    @Test
    public void test(){
        double ceil = Math.ceil(2.6);
        int ceil1 = (int) Math.ceil(2.6);
        System.out.println("ceil1 = " + ceil1);
        System.out.println("ceil = " + ceil);
    }

    @Test
    public void test2(){
        System.out.println("student = " + new Student().setName("tom").setId(123));
    }
}
