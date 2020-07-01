package com.yym.springboot.base.java.proxy.newJdkProxy.proxy;

import com.yym.springboot.base.java.proxy.newJdkProxy.UserService;
import com.yym.springboot.base.java.proxy.newJdkProxy.impl.UserServiceImpl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyHandler<T> implements InvocationHandler {
    private T targer;
    public ProxyHandler(T targer){
        this.targer = targer;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(proxy.getClass().getName());
        System.out.println(method.getName());
        System.out.println(args);
        Class<?> clazz = proxy.getClass();
        Field[] declaredFields = clazz.getDeclaredFields();
        System.out.println(declaredFields[0].getName());
        Object invoke = method.invoke(targer, args);
        return invoke;
    }

    public T getProxy(){
        return (T)Proxy.newProxyInstance(this.getClass().getClassLoader(), this.targer.getClass().getInterfaces(), this);
    }


    public static void main(String[] args) {
        // 构建代理类
        ProxyHandler<UserService> proxy = new ProxyHandler<>(new UserServiceImpl());

        System.out.println("proxy.getClass().getName() = " + proxy.getClass().getName());
        proxy.getProxy().sayHello();
    }
}
