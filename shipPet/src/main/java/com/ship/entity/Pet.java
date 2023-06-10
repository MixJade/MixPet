package com.ship.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 宠物信息表，外键用户表
 * </p>
 *
 * @author MixJade
 * @since 2023-01-18
 */
public class Pet implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 宠物的id
     */
    @TableId(value = "pet_id", type = IdType.AUTO)
    private Long petId;

    /**
     * 宠物姓名
     */
    private String petName;

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
     * 宠物主人id
     */
    private Long clientId;

    /**
     * 宠物照片名称
     */
    private String petPhoto;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 逻辑删除，默认0，填充删除日期
     */
    private String isDel;

    public Long getPetId() {
        return petId;
    }

    public void setPetId(Long petId) {
        this.petId = petId;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

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

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getPetPhoto() {
        return petPhoto;
    }

    public void setPetPhoto(String petPhoto) {
        this.petPhoto = petPhoto;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getIsDel() {
        return isDel;
    }

    public void setIsDel(String isDel) {
        this.isDel = isDel;
    }

    @Override
    public String toString() {
        return "Pet{" +
            "petId = " + petId +
            ", petName = " + petName +
            ", petVariety = " + petVariety +
            ", petSex = " + petSex +
            ", petAge = " + petAge +
            ", petStatus = " + petStatus +
            ", petInfo = " + petInfo +
            ", clientId = " + clientId +
            ", petPhoto = " + petPhoto +
            ", createTime = " + createTime +
            ", updateTime = " + updateTime +
            ", isDel = " + isDel +
        "}";
    }
}
