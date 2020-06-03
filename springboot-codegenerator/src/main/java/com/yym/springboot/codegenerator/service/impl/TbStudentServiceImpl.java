package com.yym.springboot.codegenerator.service.impl;

import com.yym.springboot.codegenerator.entity.TbStudent;
import com.yym.springboot.codegenerator.mapper.TbStudentMapper;
import com.yym.springboot.codegenerator.service.ITbStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yym
 * @since 2020-01-16
 */
@Service
public class TbStudentServiceImpl extends ServiceImpl<TbStudentMapper, TbStudent> implements ITbStudentService {

}
