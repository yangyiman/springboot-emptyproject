package com.yym.springboot.restsecurity.authority;

import com.yym.springboot.restsecurity.securitypojo.MyGrantedAuthority;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Iterator;

@Component
public class MyAccessDecisionManager implements AccessDecisionManager {
    /**
     * 此接口用户判断用户是否有权限访问该url
     *
     * @param authentication
     * @param object
     * @param configAttributes
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        if (null == configAttributes || configAttributes.size() <= 0) {
            return;
        }

        AntPathMatcher matcher = new AntPathMatcher();
        // 将用户权限和所需权限对比,匹配一个即可
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            if (authority instanceof MyGrantedAuthority) {
                // 用户权限
                MyGrantedAuthority myGrantedAuthority = (MyGrantedAuthority) authority;
                String url = myGrantedAuthority.getUrl();
                String method = myGrantedAuthority.getMethod();
                matcher = new AntPathMatcher(url);
                // 匹配url所需要的权限
                Iterator<ConfigAttribute> iterator = configAttributes.iterator();
                while (iterator.hasNext()) {
                    String tt = iterator.next().getAttribute();
                    if (matcher.match(url, tt.split(" ")[0]) &&
                            (method.equals(tt.split(" ")[1])) || "ALL".equals(method)) {
                        return;
                    }
                }
            }
        }
        /*for(Iterator<ConfigAttribute> iter = configAttributes.iterator(); iter.hasNext(); ) {
            c = iter.next();
            needRole = c.getAttribute();
            String url = needRole.split(" ")[0];
            AntPathRequestMatcher matcher = new AntPathRequestMatcher(url);
            String method = needRole.split(" ")[1];
            for(GrantedAuthority ga : authentication.getAuthorities()) {//authentication 为在注释1 中循环添加到 GrantedAuthority 对象中的权限信息集合
                if(needRole.trim().equals(ga.getAuthority())) {
                    return;
                }
            }
        }*/
        throw new AccessDeniedException("no right");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
