package com.yym.springboot.restsecurity.controller;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.alibaba.druid.stat.DruidStatManagerFacade;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

@RestController
public class DruidController {

    @GetMapping("/druid/stat")
    public Object druid(){
        return DruidStatManagerFacade.getInstance().getDataSourceStatDataList();
    }
}
