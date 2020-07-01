package com.yym.springboot.annotation.aspect;

import jdk.nashorn.internal.runtime.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class ControllerMethodAspect {

    @Pointcut("@annotation(com.yym.springboot.annotation.annotation.ControllerMethodName)")
    public void pointcut() {
    }

    ;

    /**
     * 在控制台答应调用日志: 获取方法名和方法参数
     *
     * @param point
     * @return
     */
    @Around("pointcut()")
    public Object controller(ProceedingJoinPoint point) throws Throwable {
        // 获取当前方法签名
        Signature signature = point.getSignature();
        String targetName = point.getTarget().getClass().getName();
        String methodName = point.getSignature().getName();
        Object[] arguments = point.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();

        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        methodName = method.getName();
        String[] paramsArray = methodSignature.getParameterNames();
        // 打日志
        log.info(LocalDateTime.now() + "-- {} -- {}", methodName, Arrays.toString(paramsArray));
        // 执行目标方法,并返回结果
        Object result = point.proceed();
        return result;
    }
}
