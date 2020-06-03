package com.yym.springboot.base.java.list;

import org.junit.Test;

/**
 * 测试手写的双向链表
 */
public class MyLinkedListTest {

    @Test
    public void test(){
        MyLinkedList list = new MyLinkedList();
        list.addLast("B");
        list.addLast("C");
        list.addLast("D");
        list.addFirst("A");
        System.out.println("list = " + list);
        //list.delete("A");
        //System.out.println("list = " + list);
        //list.delete("D");
        //System.out.println("list = " + list);
        list.delete("B");
        System.out.println("list = " + list);
    }

}