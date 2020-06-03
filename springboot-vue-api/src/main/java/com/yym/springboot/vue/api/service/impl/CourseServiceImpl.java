package com.yym.springboot.vue.api.service.impl;

import com.yym.springboot.vue.api.entity.Course;
import com.yym.springboot.vue.api.mapper.CourseMapper;
import com.yym.springboot.vue.api.service.ICourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yym
 * @since 2020-04-21
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {

}
