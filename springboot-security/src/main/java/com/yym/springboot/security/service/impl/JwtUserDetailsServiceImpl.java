package com.yym.springboot.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yym.springboot.security.domain.JwtUser;
import com.yym.springboot.security.entity.JdUser;
import com.yym.springboot.security.mapper.JdUserMapper;
import com.yym.springboot.security.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * springsecurity获取用户信息的实现类
 *
 * 此处加入角色时,需要加入前缀ROLE_,加入权限则不需要
 *
 *
 *  当用户登录时，authenticationManager进行响应，通过用户输入的用户名和密码，然后再根据用户定义的密码（可以加算法和盐值等）进行计算并和数据库比对，
 *  当正确时通过验证。此时myUserDetailService进行响应，根据用户名从数据库中提取该用户的权限列表，组合成UserDetails供Spring Security使用。
 *
 */
@Service
public class JwtUserDetailsServiceImpl implements JwtUserDetailsService {

    @Autowired
    private JdUserMapper jdUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        JwtUser jwtUser = jdUserMapper.selectMany(username);
        if(null==jwtUser){
            throw new UsernameNotFoundException("当前用户不存在");
        }
        if(CollectionUtils.isEmpty(jwtUser.getRoleList())){
            throw new UsernameNotFoundException("当前用户无角色");
        }
        return jwtUser;
    }
}
