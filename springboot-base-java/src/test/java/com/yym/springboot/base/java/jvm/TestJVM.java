package com.yym.springboot.base.java.jvm;

import org.junit.Test;

public class TestJVM {
    @Test
    public void test1(){
        TestJVM t = new TestJVM();
        System.out.println("t = " + t.getClass().getClassLoader());
        System.out.println("t.getClass().getClassLoader().getParent() = " + t.getClass().getClassLoader().getParent());
        System.out.println("t.getClass().getClassLoader().getParent().getParent() = " + t.getClass().getClassLoader().getParent().getParent());
        Object obj = new Object();
        System.out.println("obj.getClass().getClassLoader() = " + obj.getClass().getClassLoader());
        System.out.println("obj.getClass().getClassLoader().getParent() = " + obj.getClass().getClassLoader().getParent()); // java.lang.NullPointerException

    }
}
