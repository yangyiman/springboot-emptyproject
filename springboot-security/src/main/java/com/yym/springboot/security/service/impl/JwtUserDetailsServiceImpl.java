package com.yym.springboot.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
 */
@Service
public class JwtUserDetailsServiceImpl implements JwtUserDetailsService {

    @Autowired
    private JdUserMapper jdUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<JdUser> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(JdUser::getUsername,username);
        // 防止找到多个,所以try-catch
        try {
            JdUser user = jdUserMapper.selectOne(wrapper);
            if (user != null) {
                JwtUser jwtUser = new JwtUser();
                jwtUser.setId(user.getId());
                jwtUser.setUsername(user.getUsername());
                jwtUser.setPassword(user.getPassword());
                List<GrantedAuthority> authorities = new ArrayList<>();
                GrantedAuthority oneRole = new SimpleGrantedAuthority(user.getRole());
                authorities.add(oneRole);
                jwtUser.setAuthorities(authorities);
                return jwtUser;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
