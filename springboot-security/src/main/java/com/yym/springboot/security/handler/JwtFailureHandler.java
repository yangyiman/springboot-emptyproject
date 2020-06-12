package com.yym.springboot.security.handler;

import com.alibaba.fastjson.JSON;
import com.yym.springboot.security.utils.ResponseUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 认证失败时的处理器
 */
public class JwtFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        Map<String, Object> map = new HashMap<>();
        map.put("status", 400);
        map.put("message", "登录失败");
        map.put("cause", e.getMessage());

        ResponseUtil.genResponse(httpServletResponse,map);
    }
}
