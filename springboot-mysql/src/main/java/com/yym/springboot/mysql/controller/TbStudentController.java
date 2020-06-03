package com.yym.springboot.mysql.controller;

import com.yym.springboot.mysql.entity.TbStudent;
import com.yym.springboot.mysql.service.TbStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class TbStudentController {
    @Autowired
    private TbStudentService tbStudentService;

    @PostMapping("/insert")
    public String insertStudent(@RequestBody TbStudent tbStudent){
        tbStudentService.insertStudent(tbStudent);
        return "新增学生成功";
    }
}
