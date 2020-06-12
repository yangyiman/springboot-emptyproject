package com.yym.springboot.mysql.service;

import com.yym.springboot.mysql.entity.TbUser1;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yym.springboot.mysql.entity.TbUser2;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yym
 * @since 2020-06-04
 */
public interface ITbUser1Service extends IService<TbUser1> {
    public void insertRequired(TbUser1 user1);

    boolean registry(TbUser1 tbUser1);

    void saveNotSupport(TbUser1 user1);

    /**
     * 定时任务
     */
    void scheduleTask();
}
