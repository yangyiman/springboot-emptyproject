package com.yym.springboot.base.java.map;

import org.junit.Test;

/**
 * 测试手写的HashMap
 */
public class MyHashMapTest {
    
    @Test
    public void test(){
        MyHashMap<String,Object> map = new MyHashMap<>();
        map.put("a",1);
        map.put("b",true);
        map.put("c",'c');
        map.put("d",1.234);
        System.out.println("map = " + map);
        Object b = map.get("b");
        System.out.println("b = " + b);
    }

    @Test
    public void test1(){
        MyHashMap<String,Integer> map = new MyHashMap<>(10);
        map.put("a",1);
        map.put("b",2);
        map.put("c",3);
        map.put("d",4);
        System.out.println("map = " + map);
        Integer b = map.get("b");
        System.out.println("b = " + b);
    }

}