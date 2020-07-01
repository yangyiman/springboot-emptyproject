package com.yym.springboot.web.controller;

import com.yym.springboot.web.annotation.ToList;
import com.yym.springboot.web.entity.Student;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @GetMapping("/{id}")
    public String selectOne(@PathVariable Integer id){
        Student s = new Student().setId(id).setName("张三").setGender("男");
        return s.toString();
    }


    @GetMapping("/ss")
    public String selectTwo(@RequestParam String name,@RequestParam String gender){
        String ss = name+"  "+gender;
        System.out.println("ss = " + ss);
        return ss;
    }

    @GetMapping("/sss")
    public String selectTwo(@RequestParam List<String> list){
        System.out.println("list = " + list);
        return list.toString();
    }


}
