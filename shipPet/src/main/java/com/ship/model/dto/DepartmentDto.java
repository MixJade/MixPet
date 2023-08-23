package com.ship.model.dto;

import com.ship.model.entity.Department;

public class DepartmentDto extends Department {
    /**
     * 科室之下的人数
     */
    private int doctorNum;

    /**
     * 主任名称
     */
    private String headName;

    public int getDoctorNum() {
        return doctorNum;
    }

    public void setDoctorNum(int doctorNum) {
        this.doctorNum = doctorNum;
    }

    public String getHeadName() {
        return headName;
    }

    public void setHeadName(String headName) {
        this.headName = headName;
    }
}
