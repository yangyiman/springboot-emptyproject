package com.yym.springboot.swagger2.config;

import com.yym.springboot.swagger2.serivce.RegistryService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.*;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
public class RegistryConfig implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {

    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(RegistryService.class, () -> { return
                    uname -> {
                        Random random = new Random();
                        return random.nextInt(1024);
                    };
                }
        );
        BeanDefinition beanDefinition = builder.getRawBeanDefinition();
        ((DefaultListableBeanFactory) configurableListableBeanFactory).registerBeanDefinition("registryService", beanDefinition);
    }
}
