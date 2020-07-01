package com.yym.springboot.restsecurity.controller;

import com.yym.springboot.common.entity.ResultModel;
import com.yym.springboot.restsecurity.entity.TbMan;
import com.yym.springboot.restsecurity.service.ITbManService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private ITbManService iTbManService;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @PostMapping("/register")
    public String register(@RequestBody TbMan tbMan){
        iTbManService.save(tbMan.setPassword(encoder.encode(tbMan.getPassword())));
        return ResultModel.success200WithOperation2Json();
    }
}
