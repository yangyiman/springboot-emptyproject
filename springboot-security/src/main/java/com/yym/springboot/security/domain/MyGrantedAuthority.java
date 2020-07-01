package com.yym.springboot.security.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
public class MyGrantedAuthority implements GrantedAuthority {
    private String url;
    private String method;

    public MyGrantedAuthority() {}

    public MyGrantedAuthority(String url, String method) {
        this.url = url;
        this.method = method;
    }

    @Override
    public String getAuthority() {
        return url+" "+method;
    }
}
