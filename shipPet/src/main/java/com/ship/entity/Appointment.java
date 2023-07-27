package com.ship.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 挂号单表
 * </p>
 *
 * @author MixJade
 * @since 2023-07-27
 */
public class Appointment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 挂号单id
     */
    @TableId(value = "appointment_id", type = IdType.AUTO)
    private Integer appointmentId;

    /**
     * 用户id
     */
    private Integer clientId;

    /**
     * 宠物id
     */
    private Integer petId;

    /**
     * 就诊时间
     */
    private LocalDateTime appointmentDate;

    /**
     * 部门id
     */
    private Integer departmentId;

    /**
     * 医生id
     */
    private Integer doctorId;

    /**
     * 挂号简短信息
     */
    private String appointmentInfo;

    /**
     * 是否处理
     */
    private Boolean isSuc;

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
    private Long isDel;

    public Integer getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getPetId() {
        return petId;
    }

    public void setPetId(Integer petId) {
        this.petId = petId;
    }

    public LocalDateTime getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDateTime appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public String getAppointmentInfo() {
        return appointmentInfo;
    }

    public void setAppointmentInfo(String appointmentInfo) {
        this.appointmentInfo = appointmentInfo;
    }

    public Boolean getIsSuc() {
        return isSuc;
    }

    public void setIsSuc(Boolean isSuc) {
        this.isSuc = isSuc;
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

    public Long getIsDel() {
        return isDel;
    }

    public void setIsDel(Long isDel) {
        this.isDel = isDel;
    }

    @Override
    public String toString() {
        return "Appointment{" +
            "appointmentId = " + appointmentId +
            ", clientId = " + clientId +
            ", petId = " + petId +
            ", appointmentDate = " + appointmentDate +
            ", departmentId = " + departmentId +
            ", doctorId = " + doctorId +
            ", appointmentInfo = " + appointmentInfo +
            ", isSuc = " + isSuc +
            ", createTime = " + createTime +
            ", updateTime = " + updateTime +
            ", isDel = " + isDel +
        "}";
    }
}
