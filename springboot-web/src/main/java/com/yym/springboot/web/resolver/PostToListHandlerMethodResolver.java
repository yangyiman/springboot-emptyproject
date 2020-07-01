package com.yym.springboot.web.resolver;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yym.springboot.web.annotation.FieldList;
import com.yym.springboot.web.annotation.ListParam;
import com.yym.springboot.web.annotation.ToList;
import com.yym.springboot.web.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PostToListHandlerMethodResolver implements HandlerMethodArgumentResolver {
    // 支持此注解生效
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(ListParam.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        // 获取注解
        ListParam requestBody = parameter.getParameterAnnotation(ListParam.class);
        if (requestBody == null) {
            throw new IllegalArgumentException("Unknown parameter type [" + parameter.getParameterType().getName() + "]");
        }

        // 获取注解字段,将其构造成list赋值
        // 遍历找注解,并找到需要填写的字段名
        String fieldName = null;
        Field field = null;

        Object object = parameter.getParameterType().newInstance();
        Field[] fields = parameter.getParameterType().getDeclaredFields();
        for (Field f : fields) {
            if(f.isAnnotationPresent(FieldList.class)){
                fieldName = f.getAnnotation(FieldList.class).value();
                field = f;
                break;
            };
            f.setAccessible(true);
            f.set(object,null);
        }
        // 获取请求体中的参数
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        String body = StreamUtils.copyToString(request.getInputStream(), Charset.forName("UTF-8"));
        Type type = parameter.getGenericParameterType();
        Object o = JSON.parseObject(body, type);
        System.out.println("o = " + o);
        /*JSONObject jsonObject = JSON.parseObject(body);
        String fieldNameStr = jsonObject.getString(fieldName);
        List<String> list = new ArrayList<>();
        if(StringUtils.isNotBlank(fieldNameStr)){
            Collections.addAll(list,StringUtils.split(fieldNameStr,","));
            field.setAccessible(true);
            field.set(object,list);
        }*/
        return o;
    }
}
