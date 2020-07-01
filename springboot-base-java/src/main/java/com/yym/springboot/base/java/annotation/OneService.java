package com.yym.springboot.base.java.annotation;

public class OneService {

    @One
    private OneEntity oneEntity;

    @Override
    public String toString() {
        return "OneService{" +
                "oneEntity=" + oneEntity +
                '}';
    }
}
