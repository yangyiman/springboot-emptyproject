package com.yym.springboot.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class LogAspect {

    /**
     * 定义切入点,便于切入点表达式的重用
     */
    @Pointcut("execution(public int com.yym.springboot.aop.serivce.Calculator.add(int,int))")
    public void myExpression(){}

    //@Before("myExpression()")
    public void log(JoinPoint point){
        System.out.println("["+point.getSignature().getName()+"]方法执行了,执行参数为"+ Arrays.asList(point.getArgs()));
    }

    //@AfterReturning(value = "myExpression()",returning = "result")
    public void logReturning(JoinPoint point,Object result){
        System.out.println("["+point.getSignature().getName()+"]方法执行了,执行参数为"+ Arrays.asList(point.getArgs())+",结果为"+result);
    }

    //@AfterThrowing(value = "myExpression()",throwing = "exception")
    public void logThrowing(JoinPoint point,Exception exception){
        System.out.println("["+point.getSignature().getName()+"]方法执行了,执行参数为"+ Arrays.asList(point.getArgs())+",异常为"+exception);
    }

    /**
     * 四合一方法
     * @param point
     */
    @Around("myExpression()")
    public Object logAround(ProceedingJoinPoint point){
        Object result = null;
        try {
            // 相当于前置通知
            System.out.println("["+point.getSignature().getName()+"]方法执行了,执行参数为"+ Arrays.asList(point.getArgs()));
            // 调用方法
            result = point.proceed(point.getArgs());
            // 相当于后置通知
            System.out.println("["+point.getSignature().getName()+"]方法执行了,执行参数为"+ Arrays.asList(point.getArgs())+",结果为"+result);
        } catch (Throwable exception) {
            System.out.println("["+point.getSignature().getName()+"]方法执行了,执行参数为"+ Arrays.asList(point.getArgs())+",异常为"+exception);
        }finally {
            System.out.println("finally结束");
        }
        return result;
    }
}
