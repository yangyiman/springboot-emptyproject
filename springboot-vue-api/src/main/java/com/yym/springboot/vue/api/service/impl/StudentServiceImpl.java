package com.yym.springboot.vue.api.service.impl;

import com.yym.springboot.vue.api.entity.Student;
import com.yym.springboot.vue.api.mapper.StudentMapper;
import com.yym.springboot.vue.api.service.IStudentService;
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
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

}
