package com.yym.springboot.restsecurity.controller;


import com.yym.springboot.common.entity.ResultModel;
import com.yym.springboot.restsecurity.entity.TbBlog;
import com.yym.springboot.restsecurity.service.ITbBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yym
 * @since 2020-06-30
 */
@RestController
@RequestMapping(value = "/tb-blog",produces = "application/json;charset=utf-8")
public class TbBlogController {

    @Autowired
    private ITbBlogService iTbBlogService;

    @GetMapping("/list")
    public String list(){
        List<TbBlog> list = iTbBlogService.list();
        return ResultModel.success200WithOperation2Json(list);
    }

    @GetMapping("/{id}")
    public String selectOne(@PathVariable Integer id){
        TbBlog one = iTbBlogService.getById(id);
        return ResultModel.success200WithOperation2Json(one);
    }

    @PutMapping("/{id}")
    public String updateOne(@PathVariable Integer id,@RequestBody TbBlog tbBlog){
        iTbBlogService.updateById(tbBlog.setId(id));
        return ResultModel.success200WithOperation2Json();
    }

    @DeleteMapping("/{id}")
    public String updateOne(@PathVariable Integer id){
        iTbBlogService.removeById(id);
        return ResultModel.success200WithOperation2Json();
    }

    @PostMapping
    public String updateOne(@RequestBody TbBlog tbBlog){
        iTbBlogService.save(tbBlog);
        return ResultModel.success200WithOperation2Json();
    }
}
