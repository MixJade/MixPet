package com.forge.common;

/**
 * 返回的状态码
 */
public interface ResultConst {
    Result NO_LOGIN = new Result(401, "当前未登录"),
            NO_POWER = new Result(403, "没有角色权限"),
            LOGOUT = new Result(204, "退出登录");
}
