package com.ship.model.vo;

/**
 * 查询与用户有过联系的医生
 *
 * @param flag     排序
 * @param roleId   医生id
 * @param roleName 医生名字
 */
public record MsgNameVo(byte flag, Integer roleId, String roleName) {
}
