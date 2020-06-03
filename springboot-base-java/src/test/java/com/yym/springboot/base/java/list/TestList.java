package com.yym.springboot.base.java.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 测试值传递
 */
public class TestList {

    /**
     * 我并没有动list1,只是去删除list2的数据,结果却是list1的元素也一并删除了,所以它是引用的传递,操作的是同一内存的数据
     */
    @Test
    public void test1(){
        List<String> list1 = new ArrayList();
        Collections.addAll(list1,"1","2","3","4","5");
        List<String> list2 = list1;
        // 删除第2个
        del(list2);
        System.out.println("list1 = " + list1);
        System.out.println("list2 = " + list2);
    }

    private void del(List<String> list2) {
        System.out.println("del --  prev --- list2 = " + list2);
        list2.remove(2);
        System.out.println("del --  after --- list2 = " + list2);
    }

    @Test
    public void test2(){
        List<String> list1 = new ArrayList();
        Collections.addAll(list1,"1","2","3","4","5");
        List<String> list2 = list1;
        List<String> list3 = list1.stream().collect(Collectors.toList());
        list2.remove(0);
        System.out.println("list1 = " + list1);
        System.out.println("list2 = " + list2);
        System.out.println("list3 = " + list3);
    }
}
