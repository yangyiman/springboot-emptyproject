package com.yym.springboot.security.manager;

import com.yym.springboot.security.domain.MyGrantedAuthority;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * 判断当前用户是否有访问的权限
 *
 * 将用户权限url和method,与当前请求作对比,判断用户是否有权限访问
 *
 * 当用户点击某个功能时，触发accessDecisionManager类，该类通过decide方法对用户的资源访问进行拦截。
 * 用户点击某个功能时，实际上是请求某个URL或Action， 无论.jsp也好，.action或.do也好，在请求时无一例外的表现为URL。
 * 若用户点击了"login.action"这个URL之后，那么这个URL就跟那个Map结构的数据中的key对比，若两者相同，
 * 则根据该url提取出Map结构的数据中的value来，这说明：若要请求这个URL，必须具有跟这个URL相对应的权限值。这个权限有可能是一个单独的权限，
 * 也有可能是一个权限列表，也就是说，一个URL有可能被多种权限访问。
 */
@Component
public class MyAccessDecisionManager implements AccessDecisionManager {

    /**
     * decide方法判定当前请求路径是否拥有访问权限
     *
     * @param authentication   用户所拥有的权限
     * @param object           当前请求
     * @param configAttributes 当前url所需要的权限
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        if (configAttributes == null) {
            return;
        }
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        AntPathRequestMatcher antPathRequestMatcher = null;
        // 对比用户权限
        for (GrantedAuthority ga : authentication.getAuthorities()) {
            //String attribute = configAttribute.getAttribute();
            if (ga instanceof MyGrantedAuthority) {
                MyGrantedAuthority myGrantedAuthority = (MyGrantedAuthority) ga;
                String url = myGrantedAuthority.getUrl();
                String method = myGrantedAuthority.getMethod();
                antPathRequestMatcher = new AntPathRequestMatcher(url);
                if (antPathRequestMatcher.matches(request)) {
                    //当权限表权限的method为ALL时表示拥有此路径的所有请求方式权利。
                    if (method.equals(request.getMethod()) || "ALL".equals(method)) {
                        return;
                    }
                }
            } else if (ga.getAuthority().equals("ROLE_ANONYMOUS")) {
                return;
            } else {
                throw new AccessDeniedException("您没有访问权限");
            }
        }
        throw new AccessDeniedException("鉴权出错");
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
