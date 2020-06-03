package com.yym.springboot.sfjson.service;

public class ExceptionTest {

    public static String testException() throws Exception{
        return "Exception";
    }


    public static String testException2() throws RuntimeException{
        return "runtimeException";
    }
}
