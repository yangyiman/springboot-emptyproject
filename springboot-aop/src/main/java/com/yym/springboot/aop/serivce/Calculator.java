package com.yym.springboot.aop.serivce;

import org.springframework.stereotype.Service;

@Service
public class Calculator {

    public int add(int a,int b){
        return a+b;
    }

    public int dev(int a,int b){return  a/b;}
}
