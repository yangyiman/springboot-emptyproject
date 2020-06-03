package com.yym.springboot.mysql.controller;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yym.springboot.mysql.entity.TbClass;
import com.yym.springboot.mysql.service.TbClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/class",produces = "applicatin/json;charset=utf-8")
public class TbClassController {
    @Autowired
    private TbClassService tbClassService;

    // 新增一个班级,同时新增一个学生
    @PostMapping("/insert")
    public String insertClass(@RequestBody TbClass tbClass){
        tbClassService.insertClass(tbClass);
        return "新增班级成功";
    }

    @GetMapping("/list")
    public String selectTbClasss(){
        List<TbClass> list = tbClassService.getTbclass();
        return JSONObject.toJSONString(list);
    }

    @GetMapping("/select/{id}")
    public String selectOne(@PathVariable Integer id){
        // 测试是否可以返回null
        TbClass one = tbClassService.selectOne(id);
        System.out.println("one.getStartedAt().getClass() = " + one.getStartedAt().getClass());
        return  JSONObject.toJSONString(one);
    }

    @PutMapping("/update/{id}")
    public String updateOne(@RequestBody TbClass tbClass,@PathVariable Integer id){
        tbClass.setId(id);
        tbClassService.updateOne(tbClass);
        return "OK";
    }

    @PutMapping("/update/status/{id}")
    public String updateStatus(@PathVariable Integer id){
        String oneStr = this.selectOne(id);
        if(!StringUtils.isEmpty(oneStr)){
            TbClass tbClass = JSONObject.parseObject(oneStr, TbClass.class);
            LocalDateTime startedAt = tbClass.getStartedAt();
            if(null != startedAt && LocalDateTime.now().isAfter(startedAt)){
                tbClass.setStatus("enable");
                tbClassService.updateOne(tbClass);
            }
        }
        return "find none with id = "+id;
    }

    @GetMapping("/getOne")
    public String selectOneByIdOrClassName(@RequestParam Integer id,@RequestParam String className){
        List<TbClass> list =  tbClassService.selectOneByIdOrClassName(id,className);
        return JSON.toJSONString(list);
    }
}
