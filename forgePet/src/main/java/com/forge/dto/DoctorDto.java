package com.forge.dto;

import com.forge.entity.Doctor;

public class DoctorDto extends Doctor {
    private String departmentName;

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
