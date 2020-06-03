package com.yym.springboot.redis;

import com.yym.springboot.redis.entity.Cat;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyTest1 {
    @Test
    public void test1() throws InstantiationException, IllegalAccessException {
        List<Cat> list = new ArrayList<>();
        list.add(new Cat().setName("tom1"));
        list.add(new Cat().setName("tom2"));
        list.add(new Cat().setName("tom3"));
        list.add(new Cat().setName("tom4"));
        Cat cat = get(list);
        System.out.println("cat = " + cat);
    }

    public <T> T get(List<T> list) throws IllegalAccessException, InstantiationException {
        Iterator<T> iterator = list.iterator();
        while(iterator.hasNext()){
            T next = iterator.next();
            return next;
        }
        return null;
    }
}
