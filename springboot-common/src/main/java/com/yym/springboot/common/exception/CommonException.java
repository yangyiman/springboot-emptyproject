package com.yym.springboot.common.exception;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

public class CommonException extends Exception{
    private Integer code;

    public CommonException(String message,Integer code) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public CommonException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public CommonException(String message, Integer code,Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public CommonException(Throwable cause) {
        super(cause);
    }

    protected CommonException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
