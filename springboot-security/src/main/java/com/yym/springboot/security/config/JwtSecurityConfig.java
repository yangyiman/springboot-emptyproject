package com.yym.springboot.security.config;


import com.yym.springboot.security.filter.JwtTokenFilter;
import com.yym.springboot.security.filter.JwtUsernamePasswordFilter;
import com.yym.springboot.security.handler.*;
import com.yym.springboot.security.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * springsecurity的配置类
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtTokenFilter jwtTokenFilter;

    @Autowired
    private IgnoreUrlProperties ignoreUrlProperties;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf().disable()
                .authorizeRequests()
                // 任何以/auth开头的请求随意访问
                .antMatchers("/auth/**").permitAll()
                // 配置可以访问的路径
                .antMatchers(ignoreUrlProperties.getUrls().toArray(new String[]{})).permitAll()
                // 测试用资源，需要验证了的用户才能访问
                //.antMatchers("/jd-user/**").hasRole("ADMIN")
                // 其他都需要认证
                .anyRequest().authenticated()
                //.anyRequest().access("@myRbacServiceImpl.checkAuthority(authentication,request)")
                .and()
                // 配置登出的处理器
                .logout().logoutSuccessHandler(new JwtLogoutSeccessHandler()).permitAll()
                .and()
                // 禁用session,用ajax异步异步,就可以不用session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // 将jwt过滤器放在认证后面
                .addFilterAfter(jwtTokenFilter,UsernamePasswordAuthenticationFilter.class)
                // 配置自定义的认证过滤器
                .addFilter(customAuthenticationFilter())
                .exceptionHandling().authenticationEntryPoint(new JwtEntryPointHandler()).accessDeniedHandler(new JwtAccessDeniedHandler())
        ;


    }

    /**
     *  JSON登陆（注册登录的bean）
     */
    public JwtUsernamePasswordFilter customAuthenticationFilter() throws Exception {
        JwtUsernamePasswordFilter filterz = new JwtUsernamePasswordFilter();
        filterz.setAuthenticationSuccessHandler(new JwtSuccessHandler());
        filterz.setAuthenticationFailureHandler(new JwtFailureHandler());
        filterz.setFilterProcessesUrl("/auth/login");
        //这句很关键，重用WebSecurityConfigurerAdapter配置的AuthenticationManager，不然要自己组装AuthenticationManager
        filterz.setAuthenticationManager(authenticationManagerBean());
        return filterz;
    }




    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
}
