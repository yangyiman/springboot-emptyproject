package com.yym.springboot.security.service.impl;

import com.yym.springboot.security.entity.JdUser;
import com.yym.springboot.security.mapper.JdUserMapper;
import com.yym.springboot.security.service.IJdUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yym
 * @since 2020-06-11
 */
@Service
public class JdUserServiceImpl extends ServiceImpl<JdUserMapper, JdUser> implements IJdUserService {

}
