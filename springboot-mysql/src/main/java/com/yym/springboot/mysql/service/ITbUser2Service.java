package com.yym.springboot.mysql.service;

import com.yym.springboot.mysql.entity.TbUser2;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yym
 * @since 2020-06-04
 */
public interface ITbUser2Service extends IService<TbUser2> {
    public void insertRequired2(TbUser2 user2);

    public void insertRequired2Exception(TbUser2 user2);
}
