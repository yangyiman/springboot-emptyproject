package com.yym.springboot.security.manager;

import com.yym.springboot.security.entity.JdPermission;
import com.yym.springboot.security.mapper.JdPermissionMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.PathMatcher;
import java.util.*;

/**
 * 此方法会获取所有需要权限访问的url和method,然后判断当前访问的url是否需要权限，并且将权限返回给 MyAccessDecisionManager的decide 方法，
 * 如果不在权限表中则放行。
 */
@Component
@Primary
public class MySecurityMetaSource implements FilterInvocationSecurityMetadataSource {
    @Autowired
    private JdPermissionMapper jdPermissionMapper;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        List<ConfigAttribute> list = new ArrayList<>();
        List<JdPermission> jdPermissionList = jdPermissionMapper.selectList(null);
        ConfigAttribute attribute = null;
        for (JdPermission jdPermission : jdPermissionList) {
            list.add(new SecurityConfig(jdPermission.getPermissionUrl() + " " + jdPermission.getPermissionMethod()));
        }
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        String url = request.getRequestURI();
        String method = request.getMethod();
        String matchU = url + " " + method;
        AntPathMatcher matcher = new AntPathMatcher();
        // 判断当前url是否需要权限,需要则返回权限;不需要则返回null
        for (ConfigAttribute configAttribute : list) {
            if (StringUtils.isNotBlank(configAttribute.getAttribute())) {
                // 使用空格分隔configAttribute
                String[] s = configAttribute.getAttribute().split(" ");
                // 当方法相同或者为ALL时放行
                if (matcher.match(s[0], url) && (method.equals(s[1]) || "ALL".equals(s[1]))) {
                    return list;
                }
            }
        }
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
