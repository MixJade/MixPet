package com.ship.security.model;

/**
 * 登录所需参数
 */
public record LoginVo(String username, String password, boolean remember, RoleEnum role) {
}
