package com.yym.springboot.strategy.eenum;

import lombok.Data;

public enum  PayEnum {

    ALI_PAY("ali","支付宝支付"),
    WX_PAY("wx","微信支付") ;

    /**
     * 渠道
     */
    private String channel;
    /**
     * 描述
     */
    private String description;

    PayEnum(String channel, String description) {
        this.channel = channel;
        this.description = description;
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
}
