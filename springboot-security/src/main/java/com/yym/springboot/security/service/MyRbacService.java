package com.yym.springboot.security.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface MyRbacService {

    /**
     * 根据用户名查找用户所拥有的权限,然后跟访问路径对比,相同则为true,不同则为false
     * @param authentication
     * @param request
     * @return
     */
    boolean checkAuthority(Authentication authentication, HttpServletRequest request);
}
