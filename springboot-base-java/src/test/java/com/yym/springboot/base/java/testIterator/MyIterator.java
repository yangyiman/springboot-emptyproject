package com.yym.springboot.base.java.testIterator;

import org.junit.Test;

import java.util.List;

public class MyIterator {

    /**
     * 如果list=null.遍历时调用list.size()就会报空指针异常
     * 如果list=new Array()就不会报错,仅仅是不会到循环中去
     */
    @Test
    public void test1(){
        List list = null;
//        for (int i = 0; i < list.size(); i++) {
//
//        }

        try {
            for (Object o : list) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
