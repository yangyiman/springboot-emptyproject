package com.yym.springboot.mysql.service.impl;

import com.yym.springboot.mysql.entity.TbUser1;
import com.yym.springboot.mysql.mapper.TbUser1Mapper;
import com.yym.springboot.mysql.service.ITbUser1Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yym
 * @since 2020-06-04
 */
@Service
public class TbUser1ServiceImpl extends ServiceImpl<TbUser1Mapper, TbUser1> implements ITbUser1Service {

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void insertRequired(TbUser1 user1) {
        this.save(user1);
    }
}
