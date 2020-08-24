package com.yym.springboot.strategy.eenum;

public enum PayStrategyEnum {

    ALI_PAY("ali","支付宝支付","ali"),
    WX_PAY("wx","微信支付","wx") ;

    public static PayStrategyEnum getPayEnumByChannel(String channel){
        PayStrategyEnum[] values = PayStrategyEnum.values();
        for (PayStrategyEnum payEnum : values) {
            if (payEnum.getChannel().equalsIgnoreCase(channel)){
                return payEnum;
            }
        }
        return null;
    }

    /**
     * 渠道
     */
    private String channel;
    /**
     * 描述
     */
    private String description;
    /**
     * 容器中对应Bean的名称
     */
    private String beanName;

    PayStrategyEnum(String channel, String description,String beanName) {
        this.channel = channel;
        this.description = description;
        this.beanName = beanName;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }
}
