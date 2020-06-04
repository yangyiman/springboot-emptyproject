package com.yym.springboot.mysql.service.impl;

import com.yym.springboot.mysql.entity.TbUser1;
import com.yym.springboot.mysql.entity.TbUser2;
import com.yym.springboot.mysql.mapper.TbUser2Mapper;
import com.yym.springboot.mysql.service.ITbUser2Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yym
 * @since 2020-06-04
 */
@Service
public class TbUser2ServiceImpl extends ServiceImpl<TbUser2Mapper, TbUser2> implements ITbUser2Service {

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void insertRequired2(TbUser2 user2) {
        this.save(user2);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void insertRequired2Exception(TbUser2 user2) {
        this.save(user2);
        throw new RuntimeException();
    }
}
