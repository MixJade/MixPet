package com.forge.shiro;

/**
 * shiro角色的常量接口，配合权限注解使用
 */
public interface RoleConst {
    /**
     * 超级管理员
     */
    String ADMIN = "admin";
    /**
     * 管理员
     */
    String MANAGER = "manager";
    /**
     * 护士
     */
    String NURSE = "nurse";
    /**
     * 临时工
     */
    String LOSER = "loser";
    /**
     * 医生
     */
    String DOCTOR = "doctor";
    /**
     * 用户
     */
    String CLIENT = "client";
}
