package com.yym.springboot.restsecurity.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yym.springboot.restsecurity.entity.TbMan;
import com.yym.springboot.restsecurity.entity.TbMapManRole;
import com.yym.springboot.restsecurity.mapper.TbManMapper;
import com.yym.springboot.restsecurity.mapper.TbMapManRoleMapper;
import com.yym.springboot.restsecurity.service.ITbMapManRoleService;
import org.springframework.stereotype.Service;

@Service
public class TbMapManRoleServiceImpl extends ServiceImpl<TbMapManRoleMapper, TbMapManRole> implements ITbMapManRoleService{

}
