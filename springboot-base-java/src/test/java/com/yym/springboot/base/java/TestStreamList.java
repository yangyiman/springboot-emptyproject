package com.yym.springboot.base.java;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class TestStreamList {

    /**
     * 使用stream的filter,如果不满足,返回的是一个空list还是null ------->返回的是空数组
     */
    @Test
    public void test1() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        //List<Integer> list = null;
        List<Integer> collect = list.stream().filter(i -> i > 6).collect(Collectors.toList());
        Map<Integer, Integer> collect1 = collect.stream().filter(i -> i > 2).collect(Collectors.toMap(i -> i, one -> one));
        Set<Map.Entry<Integer, Integer>> entries = collect1.entrySet();
        System.out.println("entries = " + entries);
        System.out.println("collect1 = " + collect1);
        System.out.println("collect = " + collect);
    }

    /**
     * 是否可以使用break暂停iterator------------> 可以停止
     */
    @Test
    public void test2() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Iterator<Integer> iterator = list.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            if (next > 3) {
                System.out.println("循环次数为 = " + ++i);
                System.out.println("next = " + next);
            } else {
                continue;
            }
        }
    }

    /**
     * 是否可以停止stream循环,------------->不能使用break打断
     * 但是,可以配合filter和findFirst提前结束
     * 如: 找到i=3 就停止循环
     */
    @Test
    public void test3() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Integer integer = list.stream().filter(i -> i == 3).findFirst().get();
        System.out.println("integer = " + integer);
    }
}
