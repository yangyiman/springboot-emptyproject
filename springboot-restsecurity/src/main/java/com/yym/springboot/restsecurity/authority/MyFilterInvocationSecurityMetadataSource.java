package com.yym.springboot.restsecurity.authority;

import com.yym.springboot.common.util.SpringUtil;
import com.yym.springboot.restsecurity.entity.TbPermission;
import com.yym.springboot.restsecurity.service.impl.TbManServiceImpl;
import com.yym.springboot.restsecurity.service.impl.TbPermissionServiceImpl;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.DefaultFilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class MyFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private Map<String, List<ConfigAttribute>> map = null;
    private List<ConfigAttribute> attributeList = null;

    /**
     * 获取所有的权限
     */

    public void setMap() {
        //map = new HashMap<>();
        attributeList = new ArrayList<>();
        TbPermissionServiceImpl tbPermissionImpl = SpringUtil.getBean("tbPermissionServiceImpl", TbPermissionServiceImpl.class);
        // 所有的路径的权限 /tb-blog* GET POST ...
        List<TbPermission> list = tbPermissionImpl.list();
        list.forEach(p -> {
            ConfigAttribute arri = new SecurityConfig(p.getTbUrl() + " " + p.getTbMethod());
            attributeList.add(arri);
            List<ConfigAttribute> list1 = new ArrayList<>();
            list1.add(arri);
            //map.put(p.getTbUrl(), list1);
        });
        // 将所有的同路径的
    }

    /**
     * 拿到所有的权限,去匹配
     * 将对应的权限返回  /tb-blog Update 找是否有这个权限
     * @param object
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        //attributeList = null;
        if (null == attributeList) {
            this.setMap();
        }
        // object 中包含用户请求的request的信息
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        // 当前uri和请求方式
        String uri = request.getRequestURI();
        String method = request.getMethod();
        // 判断当前是否需要权限,如果需要,则返回
        AntPathRequestMatcher antPathRequestMatcher = null;
        List<ConfigAttribute> resultList = new ArrayList<>();
        for (ConfigAttribute attribute : attributeList) {
            String attr = attribute.getAttribute();
            antPathRequestMatcher = new AntPathRequestMatcher(attr.split(" ")[0]);
            // 匹配所有方法ALL
            if (antPathRequestMatcher.matches(request) &&
                    (method.equals(attr.split(" ")[1]) || "ALL".equals(attr.split(" ")[1]))) {
                resultList.add(attribute);
                return resultList;
            }
        }
        /*for (Map.Entry<String, List<ConfigAttribute>> entry : map.entrySet()) {
            String url = entry.getKey();
            // url相同,还需要判断请求方式
            if (new AntPathRequestMatcher(url).matches(request)) {
                // 判断请求方式
                for (ConfigAttribute attribute : entry.getValue()) {
                    String key = attribute.getAttribute();
                    if (request.getMethod().equals(key.split(" ")[1])){
                        return map.get(url);
                    }
                }

            }
        }*/
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
