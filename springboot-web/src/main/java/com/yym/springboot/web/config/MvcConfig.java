package com.yym.springboot.web.config;

import com.yym.springboot.web.resolver.ListHandlerMethodResolver;
import com.yym.springboot.web.resolver.PostToListHandlerMethodResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.nio.charset.Charset;
import java.util.List;

//@Configuration
public class MvcConfig extends WebMvcConfigurationSupport {
    /*@Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        super.addArgumentResolvers(argumentResolvers);
        //argumentResolvers.add(new ListHandlerMethodResolver());
        //argumentResolvers.add(new PostToListHandlerMethodResolver());
    }*/

    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        StringHttpMessageConverter converter = new StringHttpMessageConverter();
        converter.setDefaultCharset(Charset.forName("UTF-8"));
        converters.add(converter);
    }
}
