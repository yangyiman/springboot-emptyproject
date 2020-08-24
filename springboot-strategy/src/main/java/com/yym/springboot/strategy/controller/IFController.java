package com.yym.springboot.strategy.controller;

import com.yym.springboot.strategy.service.IPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 使用if-else方式虽然简单,但是违背了开闭原则,现在,我要新增一个银联支付,修改的方式为:
 * 1. 修改enum,新增union支付方式
 * 2. 修改PayServiceImpl,多增加一个if-else
 * 这样太过于繁琐,所以需要改进:
 * 使用策略模式改进:
 * 1. 新建一个策略接口类(下面简称策略类),定义支付方法
 * 2. 每种支付方式去实现策略类,并且定义bean名称,然后加入容器
 * 3. 修改enum类,增加一个通过支付方式的类获取对应enum方法
 * 4. 定义一个策略工厂,用于获取对应的实现类
 */
@RestController
public class IFController {
    @Autowired
    private IPayService iPayService;

    @GetMapping("/if/{channel}/{amount}")
    public String test(@PathVariable String channel,
                       @PathVariable String amount){
        return iPayService.pay(channel, amount);
    }
}
