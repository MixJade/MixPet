package com.forge.common;

/**
 * 返回的状态码
 */
public enum ResultEnum {
    NO_LOGIN(401, "当前未登录"),
    NO_POWER(403, "没有角色权限"),
    LOGOUT(204, "退出登录");

    final Integer code;
    final String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
