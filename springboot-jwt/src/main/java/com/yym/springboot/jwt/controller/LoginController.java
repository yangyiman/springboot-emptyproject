package com.yym.springboot.jwt.controller;

import com.alibaba.fastjson.JSON;
import com.yym.springboot.jwt.util.JwtUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    //令牌头名字
    public static final String AUTHORIZE_TOKEN = "Authorization";

    // 登录接口  只要密码为123123都提示登录成功
    // 用户登录,然后生成token,之后放入cookie中
    @GetMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpServletResponse response,
                        HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<>();
        if (StringUtils.isBlank(password) || !"123123".equals(password)) {
            resultMap.put("status", 401);
            resultMap.put("message", "登录失败");
            return JSON.toJSONString(resultMap);
        }
        Map<String,Object> map = new HashMap<>();
        map.put("username",username);
        // 生成token
        String token = JwtUtils.generateToken(JSON.toJSONString(map));
        // 放入cookie中
        Cookie cookie = new Cookie(AUTHORIZE_TOKEN,token);
        cookie.setPath("/");
        response.addCookie(cookie);
        resultMap.put("status", 200);
        resultMap.put("message", "登录成功");
        return JSON.toJSONString(resultMap);
    }



    // 校验token之后就提示登录正确
    // 用户使用token登录
    @GetMapping("/find")
    public String findSomething(){
        Map<String,Object> map = new HashMap<>();
        map.put("status","200");
        map.put("message", "OK");
        map.put("date", "2斤苹果");
        return JSON.toJSONString(map);
    }
}
