package com.ship.model.vo;

import com.ship.model.entity.Client;

/**
 * 附带验证码、记住我
 */
public class RegisterVo extends Client {
    private String checkCode;

    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }
}
