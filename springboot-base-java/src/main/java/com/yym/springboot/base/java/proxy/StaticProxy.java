package com.yym.springboot.base.java.proxy;

/**
 * 模拟静态代理
 *
 *  静态代理需要自己去实现,代码耦合度较高,并且维护起来麻烦,并且代码多
 *
 * 需求: 添加用户时,需要打印一句话
 * UserInterface: 用户接口
 * UserInterfaceImpl: 用户接口实现类
 * UserStaicProxy: 静态代理类
 *
 */
public class StaticProxy {
    public static void main(String[] args) {
        UserInterface userInterface = new UserInterfaceImpl();
        UserStaticProxy proxy = new UserStaticProxy(userInterface);
        // 添加用户
        // 静态代理打印日志...
        proxy.addUserProxy();
    }
}

// 静态代理
class UserStaticProxy{
    UserInterface userInterface;

    public UserStaticProxy(UserInterface userInterface){
        this.userInterface = userInterface;
    }

    public void addUserProxy(){
        userInterface.addUser();
        System.out.println("静态代理打印日志...");
    }
}
