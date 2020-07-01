package com.yym.springboot.vue.api.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yym.springboot.vue.api.entity.JdUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yym
 * @since 2020-06-22
 */
public interface IJdUserService extends IService<JdUser> {

    IPage<JdUser> pageList(Integer page, Integer pageSize);

    JdUser login(JdUser jdUser);
}
