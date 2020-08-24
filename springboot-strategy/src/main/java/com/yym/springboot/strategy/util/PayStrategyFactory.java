package com.yym.springboot.strategy.util;

import com.yym.springboot.strategy.eenum.PayStrategyEnum;
import com.yym.springboot.strategy.service.IPayStrategy;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 工厂类就是通过渠道码来获取真是的实现类
 */
@Component
public class PayStrategyFactory implements ApplicationContextAware {
    public static ApplicationContext context;

    /**
     * 通过渠道码获取具体实现类
     *
     * @param channel   渠道
     * @return
     */
    public static IPayStrategy getPayStrategy(String channel) {
        return context.getBean(PayStrategyEnum.getPayEnumByChannel(channel).getBeanName(), IPayStrategy.class);
    }

    public static IPayStrategy getPayStrategyById(String channel) {
        return context.getBean(channel, IPayStrategy.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}
