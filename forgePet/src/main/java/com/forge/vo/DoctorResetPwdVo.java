package com.forge.vo;

/**
 * 医生重置密码
 *
 * @param password  新密码
 * @param checkCode 验证码
 */
public record DoctorResetPwdVo(String password, String checkCode) {
}
