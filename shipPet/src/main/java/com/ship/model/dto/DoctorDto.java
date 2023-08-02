package com.ship.model.dto;

import com.ship.model.entity.Doctor;

public class DoctorDto extends Doctor {
    private String departmentName;

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
