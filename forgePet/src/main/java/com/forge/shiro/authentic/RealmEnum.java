package com.forge.shiro.authentic;

/**
 * 登录时所验证角色
 */
public enum RealmEnum {
    CLIENT("ClientRealm"), EMPLOYEE("EmployeeRealm"), DOCTOR("DoctorRealm");
    private final String name;

    RealmEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
