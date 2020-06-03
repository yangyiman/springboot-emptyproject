package com.yym.springboot.base.java.spring;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * 模拟spring循环依赖
 */
public class CircularDependency {
    private static Map<String, Object> map = new HashMap<>(2);

    private static <T> T getBean(Class<T> beanClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        // bean名字小写
        String beanName = beanClass.getSimpleName().toLowerCase();
        // 如果该bean已经存在,则直接返回
        if(map.containsKey(beanName)){
            return (T) map.get(beanName);
        }
        // 没有则创建
        // 设置私有构造器可访问
        Constructor<T> con = beanClass.getDeclaredConstructor();
        con.setAccessible(true);
        Object object = con.newInstance();
        // 存入缓存
        map.put(beanName, object);
        // 把所有字段当成需要注入的bean,创建并注入到当前bean中
        Field[] declaredFields = object.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            // 获取需要创建字段的class
            Class<?> fieldClass = field.getType();
            String fieldBeanName = fieldClass.getSimpleName().toLowerCase();
            // 如果没有,则继续创建
            // 如果有,则把缓存中的值注入到该field字段上
            field.set(object,map.containsValue(fieldBeanName)?map.get(fieldBeanName):getBean(fieldClass));
        }
        // 属性填充完成,返回
        return (T) object;
    }

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Class[] classes = {AA.class, BB.class};
        for (Class clazz : classes) {
            getBean(clazz);
        }
        // check
        System.out.println(getBean(BB.class).getAA() == getBean(AA.class));
        System.out.println(getBean(AA.class).getBB() == getBean(BB.class));
    }
}

// AA和BB循环依赖
class BB {
    private AA aa;

    private BB() {}

    public AA getAA() {
        return aa;
    }

    public void setAA(AA aa) {
        this.aa = aa;
    }
}
class AA {
    private BB bb;
    private AA(){};

    public BB getBB() {
        return bb;
    }

    public void setBB(BB bb) {
        this.bb = bb;
    }
}
