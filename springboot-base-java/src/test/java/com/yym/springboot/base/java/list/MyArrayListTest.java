package com.yym.springboot.base.java.list;

import org.junit.Test;

/**
 * 测试手写的ArrayList
 */
public class MyArrayListTest {

    @Test
    public void add() {
        MyArrayList list = new MyArrayList(5);
        list.add(1);
        list.add(true);
        list.add('c');
        list.add("string");
        System.out.println("list = " + list);

        // 测试delete
        list.delete(2);
        System.out.println("list = " + list);
        
        // 测试修改
        list.updateElementByIndex(0,666);
        System.out.println("list = " + list);

        // 测试get
        Object o = list.get(1);
        System.out.println("o = " + o);
    }

    @Test
    public void delete() {

    }

    @Test
    public void getIndexByElement() {
    }

    @Test
    public void updateElementByIndex() {
    }

    @Test
    public void get(){

    }
}