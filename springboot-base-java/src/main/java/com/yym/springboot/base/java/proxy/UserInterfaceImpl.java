package com.yym.springboot.base.java.proxy;


class UserInterfaceImpl implements UserInterface{
    @Override
    public void addUser() {
        System.out.println("添加用户");
    }
}
