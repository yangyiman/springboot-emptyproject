package com.yym.springboot.vue.api.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yym.springboot.common.entity.ResultModel;
import com.yym.springboot.vue.api.entity.JdBlog;
import com.yym.springboot.vue.api.service.IJdBlogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author yym
 * @since 2020-06-22
 */
@RestController
@RequestMapping("/api/v1/jd-blog")
@Api(tags = "博客相关接口")
public class JdBlogController {

    @Autowired
    private IJdBlogService iJdBlogService;

    @GetMapping("/list")
    @ApiOperation("分页查询博客")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "条数", defaultValue = "10")
    })
    public String selectPage(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        IPage<JdBlog> pageObj = iJdBlogService.pageList(page, pageSize);
        return ResultModel.success200WithOperation2Json(pageObj);
    }

    @GetMapping("/{id}")
    @ApiOperation("获取博客详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "博客id",required = true)
    })
    public String selectOne(@PathVariable("id") String blogId) {
        JdBlog one = iJdBlogService.getById(blogId);
        return ResultModel.success200WithOperation2Json(one);
    }

    @PostMapping
    @ApiOperation("创建博客")
    public String createOne(@Validated @RequestBody JdBlog jdBlog) {
        iJdBlogService.save(jdBlog);
        return ResultModel.success200WithOperation2Json();
    }

    @PutMapping("/{id}")
    @ApiOperation("修改博客")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "博客id",required = true),
    })
    public String updateOne(@PathVariable("id") Integer blogId,
                            @Validated @RequestBody JdBlog jdBlog) {
        jdBlog.setId(blogId);
        iJdBlogService.updateById(jdBlog);
        return ResultModel.success200WithOperation2Json();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除博客")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "博客id",required = true)
    })
    public String deleteOne(@PathVariable("id") Integer blogId) {
        iJdBlogService.removeById(blogId);
        return ResultModel.success200WithOperation2Json();
    }

}
