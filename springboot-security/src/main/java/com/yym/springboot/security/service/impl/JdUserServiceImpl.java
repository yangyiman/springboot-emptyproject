package com.yym.springboot.security.service.impl;

import com.yym.springboot.security.entity.JdUser;
import com.yym.springboot.security.mapper.JdUserMapper;
import com.yym.springboot.security.service.IJdUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class JdUserServiceImpl extends ServiceImpl<JdUserMapper, JdUser> implements IJdUserService {

}
