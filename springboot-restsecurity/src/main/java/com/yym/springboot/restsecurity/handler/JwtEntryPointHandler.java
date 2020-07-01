package com.yym.springboot.restsecurity.handler;

import com.yym.springboot.common.entity.ResultModel;
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
        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.getWriter().write(ResultModel.success200WithOperation2Json(map));
    }
}
