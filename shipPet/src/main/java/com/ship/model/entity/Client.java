package com.ship.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serial;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author MixJade
 * @since 2023-08-22
 */
public class Client implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableId(value = "client_id", type = IdType.AUTO)
    private Integer clientId;

    /**
     * 用户账号
     */
    private String clientUsername;

    /**
     * 用户密码
     */
    private String clientPassword;

    /**
     * 用户姓名
     */
    private String clientName;

    /**
     * 用户性别
     */
    private Boolean clientGender;

    /**
     * 用户电话
     */
    private String clientTel;

    /**
     * 用户生日
     */
    private LocalDate clientAge;

    /**
     * 用户简介
     */
    private String clientInfo;

    /**
     * 用户照片名称
     */
    private String clientPhoto;

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

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getClientUsername() {
        return clientUsername;
    }

    public void setClientUsername(String clientUsername) {
        this.clientUsername = clientUsername;
    }

    public String getClientPassword() {
        return clientPassword;
    }

    public void setClientPassword(String clientPassword) {
        this.clientPassword = clientPassword;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Boolean getClientGender() {
        return clientGender;
    }

    public void setClientGender(Boolean clientGender) {
        this.clientGender = clientGender;
    }

    public String getClientTel() {
        return clientTel;
    }

    public void setClientTel(String clientTel) {
        this.clientTel = clientTel;
    }

    public LocalDate getClientAge() {
        return clientAge;
    }

    public void setClientAge(LocalDate clientAge) {
        this.clientAge = clientAge;
    }

    public String getClientInfo() {
        return clientInfo;
    }

    public void setClientInfo(String clientInfo) {
        this.clientInfo = clientInfo;
    }

    public String getClientPhoto() {
        return clientPhoto;
    }

    public void setClientPhoto(String clientPhoto) {
        this.clientPhoto = clientPhoto;
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
        return "Client{" +
                "clientId = " + clientId +
                ", clientUsername = " + clientUsername +
                ", clientPassword = " + clientPassword +
                ", clientName = " + clientName +
                ", clientGender = " + clientGender +
                ", clientTel = " + clientTel +
                ", clientAge = " + clientAge +
                ", clientInfo = " + clientInfo +
                ", clientPhoto = " + clientPhoto +
                ", createTime = " + createTime +
                ", updateTime = " + updateTime +
                ", isDel = " + isDel +
                "}";
    }
}
