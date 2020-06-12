package com.yym.springboot.security.handler;

import com.alibaba.fastjson.JSON;
import com.yym.springboot.security.domain.JwtUser;
import com.yym.springboot.security.utils.JwtUtil;
import com.yym.springboot.security.utils.ResponseUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 验证成功,生成token,写入response中
 */
public class JwtSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
        // 生成token
        String token = JwtUtil.generateToken(jwtUser);
        httpServletResponse.setHeader(JwtUtil.TOKEN_HEADER,token);
        Map<String, Object> map = new HashMap<>();
        map.put("status", 200);
        map.put("message", "登录成功");
        map.put("JwtUser", jwtUser);
        map.put("token",token);

        ResponseUtil.genResponse(httpServletResponse,map);

    }
}
