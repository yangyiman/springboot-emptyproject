package com.yym.springboot.security.handler;

import com.alibaba.fastjson.JSON;
import com.yym.springboot.security.utils.ResponseUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 权限不足时的处理器
 */
public class JwtAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        Map<String, Object> map = new HashMap<>();
        map.put("status", 403);
        map.put("message", "权限不足,抱歉");
        map.put("cause", e.getMessage());

        ResponseUtil.genResponse(httpServletResponse,map);
    }
}
