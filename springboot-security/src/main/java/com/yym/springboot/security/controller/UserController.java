package com.yym.springboot.security.controller;

import com.yym.springboot.common.entity.ResultModel;
import com.yym.springboot.security.entity.JdUser;
import com.yym.springboot.security.mapper.JdUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private JdUserMapper jdUserMapper;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @GetMapping("/{id}")
    public String getUser(@PathVariable Integer id){
        JdUser jdUser = jdUserMapper.selectById(id);
        return ResultModel.success200WithOperation2Json(jdUser);
    }

    @GetMapping("/list")
    public String getUsers(){
        List<JdUser> jdUsers = jdUserMapper.selectList(null);
        return ResultModel.success200WithOperation2Json(jdUsers);
    }

    @PostMapping()
    public String postUser(@RequestBody JdUser jdUser){
        jdUser.setPassword(encoder.encode(jdUser.getPassword()));
        jdUserMapper.insert(jdUser);
        return ResultModel.success200WithOperation2Json();
    }

    @PutMapping("/{id}")
    public String putUser(@PathVariable Integer id,@RequestBody JdUser jdUser){
        jdUserMapper.updateById(jdUser.setId(id));
        return ResultModel.success200WithOperation2Json();
    }

    @DeleteMapping("/{id}")
    public String putUser(@PathVariable Integer id){
        jdUserMapper.deleteById(id);
        return ResultModel.success200WithOperation2Json();
    }

}
