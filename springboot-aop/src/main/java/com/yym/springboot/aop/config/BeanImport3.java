package com.yym.springboot.aop.config;

import lombok.Data;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * bean依赖注入的方式:
 *  1. 使用注解,如@Componet
 *  2. 使用@Bean
 *  3. 使用工厂FactoryBean创建
 */
// 方式3: 使用工厂
@Configuration
public class BeanImport3 {

    // 配置工厂
    public User2FactoryBean getFactoryBean(){
        return new User2FactoryBean();
    }
    // 注入bean
    public User2 getUser2(User2FactoryBean user2FactoryBean) throws Exception {
        return user2FactoryBean.getObject();
    }
}

// 实体类
@Data
class User2{
    private String name;
}

// 工厂类
class User2FactoryBean implements FactoryBean<User2>{

    @Override
    public User2 getObject() throws Exception {
        return new User2();
    }

    @Override
    public Class<?> getObjectType() {
        return User2.class;
    }
}


