package com.yym.springboot.restsecurity.filter;

import com.yym.springboot.common.util.SpringUtil;
import com.yym.springboot.restsecurity.config.SecurityIgnoreUrlProperties;
import com.yym.springboot.restsecurity.securitypojo.JwtUser;
import com.yym.springboot.restsecurity.service.impl.ManUserDetailsServiceImpl;
import com.yym.springboot.restsecurity.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 校验token
 */
public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {
    private static String FILTER_APPLIED = "__spring_security_JWTAuthenticationFilter_filterApplied";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //解决同一请求，两次经过过滤器  原因：过滤器被加载WebSecurityConfig和spring都加载了
        if (request.getAttribute(FILTER_APPLIED) != null) {
            filterChain.doFilter(request, response);
            return;
        }
        request.setAttribute(FILTER_APPLIED, true);

        // 如果有ignore的url也放行
        SecurityIgnoreUrlProperties ignoredUrlsProperties = SpringUtil.getBean(SecurityIgnoreUrlProperties.class);
        String Requesturl = request.getRequestURI();
        PathMatcher pathMatcher = new AntPathMatcher();
        if (null != ignoredUrlsProperties) {
            for (String url : ignoredUrlsProperties.getUrls()) {
                if (pathMatcher.match(url, Requesturl)) {
                    filterChain.doFilter(request, response);
                    return;
                }
            }
        }

        String token = request.getHeader(JwtUtil.TOKEN_HEADER);
        // 如果没有则跳过
        if (StringUtils.isBlank(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 有则校验
        Claims claims = JwtUtil.parseToken(token);
        String username = (String) claims.get("username");
        //把用户的信息填充到上下文中
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            ManUserDetailsServiceImpl iManUserDetailsService = SpringUtil.getBean("manUserDetailsServiceImpl", ManUserDetailsServiceImpl.class);
            UserDetails userDetails = iManUserDetailsService.loadUserByUsername(username);
            if (userDetails != null) {
                JwtUser jwtUser = (JwtUser) userDetails;
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(jwtUser, null, jwtUser.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }
}
