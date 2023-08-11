package com.ship.model.vo;

/**
 * 领养审核所需参数
 *
 * @param adoptId 领养的单号
 * @param isAdopt 是否通过
 */
public record AdoptVo(Integer adoptId, Boolean isAdopt) {
}
