package com.yym.springboot.security.service.impl;

import com.yym.springboot.security.domain.JwtUser;
import com.yym.springboot.security.domain.MyGrantedAuthority;
import com.yym.springboot.security.service.MyRbacService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@Service
public class MyRbacServiceImpl implements MyRbacService {
    @Override
    public boolean checkAuthority(Authentication authentication, HttpServletRequest request) {
        Object principal = authentication.getPrincipal();
        if(principal != null && principal instanceof UserDetails){
            String username = ((UserDetails) principal).getUsername();
            String uri = request.getRequestURI();
            String method = request.getMethod();
            AntPathMatcher matcher = new AntPathMatcher();
            Collection<? extends GrantedAuthority> list = ((UserDetails) principal).getAuthorities();
            if(!CollectionUtils.isEmpty(list)){
                for (GrantedAuthority authority : list) {
                    if(authority instanceof MyGrantedAuthority){
                        MyGrantedAuthority myGrantedAuthority = (MyGrantedAuthority) authority;
                        if (matcher.match(myGrantedAuthority.getUrl(),uri) && method.equals(myGrantedAuthority.getMethod())) {
                            return true;
                        }
                    }
                }
            }else {
                // 从数据库查
                return true;
            }
        }
        return false;
    }
}
