package com.ship.model.vo;

/**
 * 医生处理自己的挂号
 *
 * @param appointId 挂号单ID
 * @param isSuc     处理结果
 */
public record DealAppointVo(Integer appointId, boolean isSuc) {
}
