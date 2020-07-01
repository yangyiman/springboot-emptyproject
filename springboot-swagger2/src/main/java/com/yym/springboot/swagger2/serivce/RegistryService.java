package com.yym.springboot.swagger2.serivce;

/**
 * 动态注册这个接口的实现,beanName=registryService
 */
public interface RegistryService {
    int getAge(String username);
}
