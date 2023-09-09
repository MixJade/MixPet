package com.ship.model.dto;

/**
 * 查询与用户有过联系的医生
 *
 * @param flag      排序
 * @param roleId    医生id
 * @param roleName  医生名字
 * @param rolePhoto 医生照片
 */
public record MsgNameDo(byte flag, Integer roleId, String roleName, String rolePhoto) {
}
