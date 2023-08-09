package com.ship.model.dto;

import com.ship.model.entity.Department;

public class DepartmentDto extends Department {
    /**
     * 科室之下的人数
     */
    private int doctorNum;

    public int getDoctorNum() {
        return doctorNum;
    }

    public void setDoctorNum(int doctorNum) {
        this.doctorNum = doctorNum;
    }
}
