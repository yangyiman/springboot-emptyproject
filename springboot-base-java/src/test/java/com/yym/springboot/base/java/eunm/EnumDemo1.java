package com.yym.springboot.base.java.eunm;

/**
 * 测试枚举类型,其实就是本类的一个字段
 */
public class EnumDemo1 {
    public static final EnumDemo1 ONE = new EnumDemo1(1,"第一个");
    public static final EnumDemo1 TWO = new EnumDemo1(2,"第二个");
    public static final EnumDemo1 THREE = new EnumDemo1(3,"第三个");

    private Integer code;
    private String msg;

    private EnumDemo1(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }
}
