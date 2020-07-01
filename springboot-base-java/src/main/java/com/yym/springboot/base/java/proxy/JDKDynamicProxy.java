package com.yym.springboot.base.java.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 其实静态代理和动态代理其底层原理都是一样的,都需要生成一个代理对象,然后帮我们去调用相关方法,只不过静态代理的代理对象是我们硬编码自己实现
 * 而动态代理则是动态生成的
 * 如果目标对象有需要实现的接口,则使用JDK动态代理
 * JDk帮我们实现了动态代理，使用的是newProxyInstance方法
 * public static Object newProxyInstance(ClassLoader loader,Class<?>[] interfaces,InvocationHandler h)
 * 该方法中接收三个参数：
 * ClassLoader loader,:指定当前目标对象使用类加载器  boostrap classloader/ extentions classloader appclassloader
 * Class<?>[] interfaces,:代理类需要实现的接口列表   被代理对象的接口列表
 * InvocationHandler h:调用处理程序,将目标对象的方法分派到该调用处理程序
 *
 * 此处为UserInterfaceImpl生成一个代理类,还是实现新增用户打印一句话
 */
public class JDKDynamicProxy {
    public static void main(String[] args) {
        UserDynamicProxy proxy = new UserDynamicProxy();
        UserInterface userInterface = (UserInterface)proxy.createProxy(new UserInterfaceImpl());
        userInterface.addUser();
    }
}

/**
 * 生成Jdk动态代理
 */
class UserDynamicProxy implements InvocationHandler{

    // 目标对象
    Object object;

    /**
     * 生成代理对象
     * @param object 目标对象
     * @return
     */
    public Object createProxy(Object object){
        this.object = object;
        return Proxy.newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 调用方法得到结果
        Object result = method.invoke(object, args);
        System.out.println("使用JDK动态代理得到的结果...");
        return null;
    }
}

