package com.yym.springboot.web.aop;

import com.yym.springboot.web.entity.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * 用户修改对象的值
 */
@Component
@Aspect
public class AspectArgument {
    // 这个注解
    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void cutpoint(){};

    @Before("cutpoint()")
    public void changStringToList(JoinPoint point) throws Throwable {
        System.out.println(point.getArgs());
    }
}
