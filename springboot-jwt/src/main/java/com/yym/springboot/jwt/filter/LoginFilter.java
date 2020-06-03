package com.yym.springboot.jwt.filter;

import com.yym.springboot.jwt.controller.LoginController;
import com.yym.springboot.jwt.util.JwtUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebFilter(urlPatterns = "/*",filterName = "loginFilter")
public class LoginFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("filterConfig = " + filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        res.setContentType("text/html;charset=utf-8");
        // 忽略请求/login
        String requestURI = req.getRequestURI();
        if("/login".equals(requestURI)){
            chain.doFilter(req,res);
            return;
        }
        // 从header中
        String header = req.getHeader(LoginController.AUTHORIZE_TOKEN);
        if(StringUtils.isNotBlank(header)){
            JwtUtils.parseToken(header);
            chain.doFilter(req,res);
            return;
        }
        // 从cookie中获取
        Cookie[] cookies = req.getCookies();
        if(cookies != null && cookies.length > 0){
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals(LoginController.AUTHORIZE_TOKEN)){
                    JwtUtils.parseToken(cookie.getValue());
                    chain.doFilter(req,res);
                    return;
                }
            }
        }
        // 从参数中获取
        String parameter = req.getParameter(LoginController.AUTHORIZE_TOKEN);
        if(StringUtils.isNotBlank(parameter)){
            JwtUtils.parseToken(header);
            chain.doFilter(req,res);
            return;
        }
        // 报错
        throw new RuntimeException("登录失败");
    }

    @Override
    public void destroy() {
        System.out.println("true = " + true);
    }

}
