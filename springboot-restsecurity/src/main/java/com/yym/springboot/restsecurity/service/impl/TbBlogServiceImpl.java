package com.yym.springboot.restsecurity.service.impl;

import com.yym.springboot.restsecurity.entity.TbBlog;
import com.yym.springboot.restsecurity.mapper.TbBlogMapper;
import com.yym.springboot.restsecurity.service.ITbBlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
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
public class TbBlogServiceImpl extends ServiceImpl<TbBlogMapper, TbBlog> implements ITbBlogService {

}
