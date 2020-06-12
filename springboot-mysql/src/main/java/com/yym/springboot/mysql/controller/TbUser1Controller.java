package com.yym.springboot.mysql.controller;


import com.yym.springboot.mysql.entity.TbUser1;
import com.yym.springboot.mysql.service.ITbUser1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yym
 * @since 2020-06-04
 */
@RestController
@RequestMapping("/mysql/tb-user1")
public class TbUser1Controller {

    @Autowired
    private ITbUser1Service iTbUser1Service;
    /**
     * 模拟注册,注册可以加积分,然后可以生成日志文件
     * 要求:
     *      注册失败,不加积分,但是日志文件需要生成
     *      积分失败,但是不能影响注册,日志项目也需要生成
     */
    @PostMapping("/reg")
    public String registry(@RequestBody TbUser1 tbUser1){
        boolean isRegistry = iTbUser1Service.registry(tbUser1);
        if(isRegistry){
            return "注册成功";
        }else {
            return "注册失败";
        }
    }
}
