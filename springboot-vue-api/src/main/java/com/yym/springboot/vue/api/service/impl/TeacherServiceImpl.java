package com.yym.springboot.vue.api.service.impl;

import com.yym.springboot.vue.api.entity.Teacher;
import com.yym.springboot.vue.api.mapper.TeacherMapper;
import com.yym.springboot.vue.api.service.ITeacherService;
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
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements ITeacherService {

}
