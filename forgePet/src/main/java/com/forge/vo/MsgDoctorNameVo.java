package com.forge.vo;

/**
 * 查询与用户有过联系的医生
 *
 * @param flag       排序
 * @param doctorId   医生id
 * @param doctorName 医生名字
 */
public record MsgDoctorNameVo(byte flag, Long doctorId, String doctorName) {
}
