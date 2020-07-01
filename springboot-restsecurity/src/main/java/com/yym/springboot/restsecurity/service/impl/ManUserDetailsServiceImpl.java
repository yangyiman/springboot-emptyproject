package com.yym.springboot.restsecurity.service.impl;

import com.yym.springboot.restsecurity.entity.TbMan;
import com.yym.springboot.restsecurity.mapper.TbManMapper;
import com.yym.springboot.restsecurity.securitypojo.JwtUser;
import com.yym.springboot.restsecurity.service.IManUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 获取用户权限
 */
@Service
public class ManUserDetailsServiceImpl implements IManUserDetailsService {
    @Autowired
    private TbManMapper tbManMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TbMan tbMan = tbManMapper.selectOneByUsername(username);
        if (tbMan == null) {
            throw new UsernameNotFoundException("用户不存在,请刷新后重试");
        }
        JwtUser jwtUser = new JwtUser();
        jwtUser.setId(tbMan.getId());
        jwtUser.setUsername(tbMan.getUsername());
        jwtUser.setPassword(tbMan.getPassword());
        jwtUser.setPermissions(tbManMapper.selectPermissionByUsername(username));
        return jwtUser;
    }
}
