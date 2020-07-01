package com.yym.springboot;

import org.junit.Test;
import org.springframework.util.AntPathMatcher;

public class MyTest {

    @Test
    public void test1(){
        AntPathMatcher path = new AntPathMatcher();
        String base = "/a";
        String tt = "/a/**";
        boolean match = path.match(tt, base);
        System.out.println("match = " + match);
    }
}
