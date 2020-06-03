package com.yym.springboot.base.java.iterface.impl;

import com.yym.springboot.base.java.iterface.C;

public class CImp extends TopImp implements C {
    @Override
    public void methodC() {
        System.out.println("我继承了topImp类,并且实现了C接口");
    }
}
