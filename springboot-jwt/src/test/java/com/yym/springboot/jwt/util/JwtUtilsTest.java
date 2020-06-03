package com.yym.springboot.jwt.util;

import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Claims;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class JwtUtilsTest {

    @Test
    public  void main() {
        Map<String,Object> map = new HashMap<>();
        map.put("username","yym");
        map.put("age","123");
        String s = JwtUtils.generateToken(JSON.toJSONString(map));
        System.out.println("s = " + s);
    }

    @Test
    public void test(){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ5eW0iLCJzdWIiOiJ7XCJhZ2VcIjpcIjEyM1wiLFwidXNlcm5hbWVcIjpcInl5bVwifSIsImV4cCI6MTU4Mzg5Nzc5OSwiaWF0IjoxNTgzODk1OTk5fQ.htoEn4G50xIQ1yrTpYRu1rjSBMJvqBQJSUisuf02oa8";
        Claims claims = JwtUtils.parseToken(token);
        System.out.println("claims = " + claims);
    }

}