package com.yym.springboot.vue.api.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yym.springboot.common.entity.ResultModel;
import com.yym.springboot.vue.api.entity.JdBlog;
import com.yym.springboot.vue.api.entity.JdUser;
import com.yym.springboot.vue.api.service.IJdUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yym
 * @since 2020-06-22
 */
@RestController
@RequestMapping("/api/jd-user")
@Api(tags = "用户相关接口")
public class JdUserController {

    @Autowired
    private IJdUserService iJdUserService;

    @GetMapping("/list")
    @ApiOperation("分页查询用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "条数", defaultValue = "10")
    })
    public String selectPage(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        IPage<JdUser> pageObj = iJdUserService.pageList(page, pageSize);
        return ResultModel.success200WithOperation2Json(pageObj);
    }

    @GetMapping("/{id}")
    @ApiOperation("获取用户详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id",required = true)
    })
    public String selectOne(@PathVariable("id") String id) {
        JdUser one = iJdUserService.getById(id);
        return ResultModel.success200WithOperation2Json(one);
    }

    @PostMapping
    @ApiOperation("创建用户")
    public String createOne(@Validated @RequestBody JdUser jdUser) {
        iJdUserService.save(jdUser);
        return ResultModel.success200WithOperation2Json();
    }

    @PutMapping("/{id}")
    @ApiOperation("修改用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id",required = true)
    })
    public String updateOne(@PathVariable("id") Integer id,
                            @Validated @RequestBody JdUser jdUser) {
        jdUser.setId(id);
        iJdUserService.updateById(jdUser);
        return ResultModel.success200WithOperation2Json();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id",required = true)
    })
    public String deleteOne(@PathVariable("id") Integer id) {
        iJdUserService.removeById(id);
        return ResultModel.success200WithOperation2Json();
    }

}
