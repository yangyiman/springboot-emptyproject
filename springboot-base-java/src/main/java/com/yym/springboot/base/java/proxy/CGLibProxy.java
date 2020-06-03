package com.yym.springboot.base.java.proxy;


import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CGLib又叫子类代理,它是在内存中构建一个子类继承目标对象,从而实现对目标对象功能的扩充,所有目标对象不能被final修饰,方法不能被final和static修饰
 *
 * 静态代理和JDK动态代理,其目标对象都需要去实现接口,而CGLib代理的目标对象不需要继承对象
 *
 * 需求: 为UserCGLibClass创建代理对象,并且调用方法时打印日志
 */
public class CGLibProxy {

    public static void main(String[] args) {
        UserCGLibProxy proxy = new UserCGLibProxy();
        UserCGLibClass cgLibUserClass = (UserCGLibClass)proxy.createProxy(new UserCGLibClass());
        cgLibUserClass.addUser();
    }
}
class UserCGLibProxy implements MethodInterceptor {
    // 目标对象
    Object object;

    public Object createProxy(Object object){
        this.object = object;
        // 创建增强器
        Enhancer enhancer = new Enhancer();
        // 设置父类
        enhancer.setSuperclass(this.object.getClass());
        // 设置回调函数
        enhancer.setCallback(this);
        // 创建并返回子类对象
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Object result = methodProxy.invokeSuper(o, objects);
        System.out.println("调动cglib代理...");
        return result;
    }
}
