package com.ship.vo;

/**
 * 查询与用户有过联系的医生
 *
 * @param flag       排序
 * @param clientId   医生id
 * @param clientName 医生名字
 */
public record MsgClientNameVo(byte flag, Long clientId, String clientName) {
}
