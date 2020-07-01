package com.yym.springboot.web.resolver;

import com.yym.springboot.web.annotation.ListParam;
import com.yym.springboot.web.annotation.ToList;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListHandlerMethodResolver implements HandlerMethodArgumentResolver {
    // 支持此注解生效
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(ToList.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        // 获取注解
        ToList param = parameter.getParameterAnnotation(ToList.class);
        String parameterName = parameter.getParameterName();
        if (param == null) {
            throw new IllegalArgumentException("Unknown parameter type [" + parameter.getParameterType().getName() + "]");
        }

        String list = webRequest.getParameter("list");
        List<String> list1 = new ArrayList<>();
        if (StringUtils.isNotBlank(list)) {
            Collections.addAll(list1, StringUtils.split(list, ","));
        }
        return list1;
    }
}
