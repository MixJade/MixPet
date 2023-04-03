package com.forge.dto;

import java.time.LocalDate;

/**
 * 被寄养的宠物信息
 */
public class FosterPetDto extends FosterDto {
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

    /**
     * 宠物状态
     */
    private String petStatus;

    /**
     * 宠物动态
     */
    private String petInfo;
    /**
     * 宠物照片名称
     */
    private String petPhoto;

    public String getPetVariety() {
        return petVariety;
    }

    public void setPetVariety(String petVariety) {
        this.petVariety = petVariety;
    }

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

    public String getPetStatus() {
        return petStatus;
    }

    public void setPetStatus(String petStatus) {
        this.petStatus = petStatus;
    }

    public String getPetInfo() {
        return petInfo;
    }

    public void setPetInfo(String petInfo) {
        this.petInfo = petInfo;
    }

    public String getPetPhoto() {
        return petPhoto;
    }

    public void setPetPhoto(String petPhoto) {
        this.petPhoto = petPhoto;
    }
}
