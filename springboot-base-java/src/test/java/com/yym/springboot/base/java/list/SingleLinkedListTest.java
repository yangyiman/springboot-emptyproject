package com.yym.springboot.base.java.list;

import org.junit.Test;

/**
 * 测试手写的单向链表
 */
public class SingleLinkedListTest {

    @Test
    public void test(){
        SingleLinkedList list = new SingleLinkedList();
        list.addLast("B");
        list.addLast("C");
        list.addLast("D");
        list.addFirst("A");
        System.out.println("list = " + list);
    }

}