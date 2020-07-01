package com.yym.springboot.security.domain;

import com.yym.springboot.security.entity.JdPermission;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class JwtUser extends AuthUser implements UserDetails {

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = null;
        List<JdPermission> permissionList = this.getPermissionList();
        if(!CollectionUtils.isEmpty(permissionList)){
            authorities = new ArrayList<>(permissionList.size());
            for (JdPermission jdPermission : permissionList) {
                if(StringUtils.isNotBlank(jdPermission.getPermissionUrl()) && StringUtils.isNotBlank(jdPermission.getPermissionMethod())){
                    authorities.add(new MyGrantedAuthority(jdPermission.getPermissionUrl(),jdPermission.getPermissionMethod()));
                }
            }
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getUsername();
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
}
