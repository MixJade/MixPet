package com.forge.dto;

import java.time.LocalDate;

/**
 * 已登录医生查询订单
 */
public class AppointmentDto2 extends AppointmentDto {
    /**
     * 宠物照片名称
     */
    private String petPhoto;
    /**
     * 宠物的品种
     */
    private String petVariety;
    /**
     * 宠物性别，0母1公
     */
    private Boolean petSex;
    /**
     * 宠物生日
     */
    private LocalDate petAge;

    public Boolean getPetSex() {
        return petSex;
    }

    public void setPetSex(Boolean petSex) {
        this.petSex = petSex;
    }

    public LocalDate getPetAge() {
        return petAge;
    }

    public void setPetAge(LocalDate petAge) {
        this.petAge = petAge;
    }

    public String getPetVariety() {
        return petVariety;
    }

    public void setPetVariety(String petVariety) {
        this.petVariety = petVariety;
    }

    public String getPetPhoto() {
        return petPhoto;
    }

    public void setPetPhoto(String petPhoto) {
        this.petPhoto = petPhoto;
    }
}
