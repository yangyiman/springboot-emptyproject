package com.yym.springboot.restsecurity.securitypojo;

import com.yym.springboot.restsecurity.entity.TbMan;
import com.yym.springboot.restsecurity.entity.TbPermission;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JwtUser extends TbMan implements UserDetails {
    private List<TbPermission> permissions;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<MyGrantedAuthority> myGrantedAuthorities = new ArrayList<>();
        this.permissions
                .stream()
                .map(p -> new MyGrantedAuthority(p.getTbUrl(), p.getTbMethod()))
                .forEach(g -> myGrantedAuthorities.add(g));
        return myGrantedAuthorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public List<TbPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<TbPermission> permissions) {
        this.permissions = permissions;
    }
}