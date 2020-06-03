package com.yym.springboot.vue.api.controller;


import com.yym.springboot.vue.api.entity.Student;
import com.yym.springboot.vue.api.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yym
 * @since 2020-04-21
 */
@RestController
@RequestMapping("/api/student")
@CrossOrigin
public class StudentController {
    @Autowired
    private IStudentService iStudentService;

    @GetMapping("/all")
    public List<Student> getAll(){
        return iStudentService.list();
    }

    @GetMapping("/{studentId}")
    public Student getOne(@PathVariable("studentId") String studentId){
        return iStudentService.getById(studentId);
    }

    @DeleteMapping("/{studentId}")
    public String deleteByStudentId(@PathVariable String studentId){
        boolean isDelete = iStudentService.removeById(studentId);
        return isDelete?"删除成功":"删除失败";
    }

    @PutMapping("/{studentId}")
    public String updateStudentByStudentId(@RequestBody Student student,@PathVariable String studentId){
        student.setSId(studentId);
        boolean isUpdate = iStudentService.updateById(student);
        return isUpdate?"修改成功":"修改失败";
    }

    @PostMapping("/insert")
    public String insertOne(){
        Student ss = new Student();
        Random r = new Random();
        ss.setSId(r.nextInt(1000000)+"");
        ss.setSSex(r.nextInt(2)==0?"女":"男");
        ss.setSBirth("199"+r.nextInt(10)+"-"+(r.nextInt(12)+1)+"-"+(r.nextInt(30)+1));
        ss.setSName("同学" + r.nextInt(5000));
        boolean save = iStudentService.save(ss);
        return save?"新增成功":"新增失败";
    }

}
