package com.yym.springboot.restsecurity.service.impl;

import com.yym.springboot.restsecurity.entity.TbMan;
import com.yym.springboot.restsecurity.mapper.TbManMapper;
import com.yym.springboot.restsecurity.service.ITbManService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yym
 * @since 2020-06-30
 */
@Service
public class TbManServiceImpl extends ServiceImpl<TbManMapper, TbMan> implements ITbManService {

}
