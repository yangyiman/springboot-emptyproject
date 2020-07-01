package com.yym.springboot.base.java.annotation;

import java.lang.reflect.Field;

public class OneReflect {

    /**
     * 直接使用field的set,相当于.,可以不使用set方法
     */

    public void getOne(OneService one) throws IllegalAccessException, InstantiationException {
        Field[] fields = one.getClass().getDeclaredFields();
        for (Field field : fields) {
            if(field.isAnnotationPresent(One.class)){
                Object fieldObj = field.getType().newInstance();
                field.setAccessible(true);
                field.set(one,fieldObj);
                break;
            }
        }
    }

    public static void main(String[] args) {
        OneReflect oneReflect = new OneReflect();
        try {
            OneService one = new OneService();
            oneReflect.getOne(one);
            System.out.println("one = " + one);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
