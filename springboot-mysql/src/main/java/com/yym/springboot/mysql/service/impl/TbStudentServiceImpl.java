package com.yym.springboot.mysql.service.impl;

import com.yym.springboot.mysql.entity.TbStudent;
import com.yym.springboot.mysql.mapper.TbStudentMapper;
import com.yym.springboot.mysql.service.TbStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TbStudentServiceImpl implements TbStudentService {
    @Autowired
    private TbStudentMapper tbStudentMapper;

    @Override
    public void insertStudent(TbStudent tbStudent) {
        tbStudentMapper.insert(tbStudent);
    }
}
