package com.yym.springboot.restsecurity.config;

import com.yym.springboot.restsecurity.authority.MyAccessDecisionManager;
import com.yym.springboot.restsecurity.authority.MyFilterInvocationSecurityMetadataSource;
import com.yym.springboot.restsecurity.filter.JwtAuthenticationFilter;
import com.yym.springboot.restsecurity.filter.JwtTokenAuthenticationFilter;
import com.yym.springboot.restsecurity.authority.MyFilterSecurityInterceptor;
import com.yym.springboot.restsecurity.handler.*;
import com.yym.springboot.restsecurity.service.IManUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.Filter;

@Configuration
public class SecutiryConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private IManUserDetailsService iManUserDetailsService;

    @Autowired
    private SecurityIgnoreUrlProperties securityIgnoreUrlProperties;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/auth/**").permitAll()
                .antMatchers(securityIgnoreUrlProperties.getUrls().toArray(new String[]{})).permitAll()
                .anyRequest().authenticated();
        http.cors().and().csrf().disable();
        http.logout().logoutSuccessHandler(new MyLogoutSuccessHandler()).permitAll().and()
                // 禁用session,用ajax异步异步,就可以不用session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterAt(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterAt(myFilterSecurityInterceptor(), FilterSecurityInterceptor.class);
        http.addFilterBefore(new JwtTokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.exceptionHandling().accessDeniedHandler(new JwtAccessDeniedHandler()).authenticationEntryPoint(new JwtEntryPointHandler());
    }

    public JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter();
        jwtAuthenticationFilter.setAuthenticationFailureHandler(new JwtFailureHandler());
        jwtAuthenticationFilter.setAuthenticationSuccessHandler(new JwtSuccessAuthenticationHandler());
        jwtAuthenticationFilter.setAuthenticationManager(authenticationManagerBean());
        return jwtAuthenticationFilter;
    }

    public Filter myFilterSecurityInterceptor(){
        MyFilterSecurityInterceptor myFilterSecurityInterceptor = new MyFilterSecurityInterceptor(new MyAccessDecisionManager(), new MyFilterInvocationSecurityMetadataSource());
        return myFilterSecurityInterceptor;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
        auth.userDetailsService(iManUserDetailsService).passwordEncoder(encoder());
    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

}
