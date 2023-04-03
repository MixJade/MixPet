package com.forge.shiro.authentic;

import com.forge.vo.LoginVo;
import org.apache.shiro.authc.UsernamePasswordToken;


public class MyToken extends UsernamePasswordToken {
    private final RealmEnum realmEnum;

    public MyToken(LoginVo loginVo, RealmEnum realmEnum) {
        super(loginVo.username(), loginVo.password(), loginVo.remember());
        this.realmEnum = realmEnum;
    }

    public RealmEnum getLoginType() {
        return realmEnum;
    }

}