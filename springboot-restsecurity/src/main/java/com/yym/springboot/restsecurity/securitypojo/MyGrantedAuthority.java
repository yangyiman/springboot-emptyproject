package com.yym.springboot.restsecurity.securitypojo;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
public class MyGrantedAuthority implements GrantedAuthority {
    private String url;
    private String method;
    @Override
    public String getAuthority() {
        return url+" "+method;
    }

    public MyGrantedAuthority(String url, String method) {
        this.url = url;
        this.method = method;
    }

    public MyGrantedAuthority() {}
}
