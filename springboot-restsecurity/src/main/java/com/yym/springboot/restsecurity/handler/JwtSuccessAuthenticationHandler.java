package com.yym.springboot.restsecurity.handler;

import com.yym.springboot.common.entity.ResultModel;
import com.yym.springboot.restsecurity.securitypojo.JwtUser;
import com.yym.springboot.restsecurity.util.JwtUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录成功之后,生成token
 */
public class JwtSuccessAuthenticationHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
        // 通过jwtUser设置token,2个小时,并返回
        String token = JwtUtil.genericToken(jwtUser,null);
        response.setHeader(JwtUtil.TOKEN_HEADER,token);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(ResultModel.success200WithOperation2Json(token));
    }
}
