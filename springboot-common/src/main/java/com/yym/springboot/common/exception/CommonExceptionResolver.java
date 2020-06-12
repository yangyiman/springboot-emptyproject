package com.yym.springboot.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class CommonExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception ex) {
        if (ex instanceof CommonException) {
            log.error("CommonException for {}", ex);
            ModelAndView mav = new ModelAndView(new MappingJackson2JsonView());
            mav.addObject("code", ((CommonException) ex).getCode());
            mav.addObject("msg", ((CommonException) ex).getMessage());
            httpServletResponse.setStatus(400);
            return mav;
        } else {
            log.error("Internal Server Error for {}", ex);
            ModelAndView mav = new ModelAndView(new MappingJackson2JsonView());
            mav.addObject("code", 500);
            mav.addObject("msg", "Server error, please contact administrator");
            httpServletResponse.setStatus(500);
            return mav;
        }
    }
}
