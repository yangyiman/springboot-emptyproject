package com.yym.springboot.jwt.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            // 当前controller的方法
            log.info("当前拦截的方法为：{}",handlerMethod.getMethod().getName());
            // 当前controller方法的参数个数
            log.info("当前拦截的方法参数长度为：{}",handlerMethod.getMethod().getParameters().length);
            // 当前controller的类名
            log.info("当前拦截的方法为：{}",handlerMethod.getBean().getClass().getName());
            System.out.println("开始拦截---------");
            String uri = request.getRequestURI();
            System.out.println("拦截的uri："+uri);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
