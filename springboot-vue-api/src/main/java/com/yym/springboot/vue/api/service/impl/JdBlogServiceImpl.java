package com.yym.springboot.vue.api.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yym.springboot.vue.api.entity.JdBlog;
import com.yym.springboot.vue.api.mapper.JdBlogMapper;
import com.yym.springboot.vue.api.service.IJdBlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yym
 * @since 2020-06-22
 */
@Service
public class JdBlogServiceImpl extends ServiceImpl<JdBlogMapper, JdBlog> implements IJdBlogService {

    @Override
    public IPage<JdBlog> pageList(Integer page, Integer pageSize) {
        Page<JdBlog> pageObj = new Page<>(page, pageSize);
        return this.page(pageObj);
    }
}
