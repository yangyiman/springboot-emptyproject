package com.yym.springboot.security.handler;

import com.alibaba.fastjson.JSON;
import com.yym.springboot.security.utils.ResponseUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 没有登录时的处理器
 */
public class JwtEntryPointHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        Map<String, Object> map = new HashMap<>();
        map.put("status", 401);
        map.put("message", "请登录后访问");
        map.put("cause", e.getMessage());

        ResponseUtil.genResponse(httpServletResponse,map);
    }
}
