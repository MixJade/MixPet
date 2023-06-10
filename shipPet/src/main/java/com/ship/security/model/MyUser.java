package com.ship.security.model;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public record MyUser(String username,
                     String password,
                     List<GrantedAuthority> auth,
                     RoleEnum roleEnum,
                     // TODO 这里也许可以优化，只存用户ID
                     Object role) implements UserDetails {
    // 授权信息集合
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return auth;
    }

    // 获取密码
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    // 获取用户名
    public String getUsername() {
        return username;
    }

    @Override
    // 用户的帐户是否未过期。即未过期则返回true
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    // 用户是否未锁定。无法对锁定的用户进行身份验证，如果用户未被锁定，则返回true
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    // 用户的凭据（密码）是否未过期，即未过期则返回true
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    // 用户是启用还是禁用，如果启用了用户则返回true
    public boolean isEnabled() {
        return true;
    }
}
