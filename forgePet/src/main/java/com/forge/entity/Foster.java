package com.forge.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 寄养表
 * </p>
 *
 * @author MixJade
 * @since 2023-01-18
 */
public class Foster implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 寄养表的id
     */
    @TableId(value = "foster_id", type = IdType.AUTO)
    private Long fosterId;

    /**
     * 订单编号
     */
    private String fosterCode;

    /**
     * 寄养宠物的id
     */
    private Long petId;

    /**
     * 寄养人id
     */
    private Long clientId;

    /**
     * 寄养到期时间
     */
    private LocalDate fosterTerm;

    /**
     * 寄养押金
     */
    private Integer fosterMoney;

    /**
     * 订单备注
     */
    private String fosterInfo;

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

    public Long getFosterId() {
        return fosterId;
    }

    public void setFosterId(Long fosterId) {
        this.fosterId = fosterId;
    }

    public String getFosterCode() {
        return fosterCode;
    }

    public void setFosterCode(String fosterCode) {
        this.fosterCode = fosterCode;
    }

    public Long getPetId() {
        return petId;
    }

    public void setPetId(Long petId) {
        this.petId = petId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public LocalDate getFosterTerm() {
        return fosterTerm;
    }

    public void setFosterTerm(LocalDate fosterTerm) {
        this.fosterTerm = fosterTerm;
    }

    public Integer getFosterMoney() {
        return fosterMoney;
    }

    public void setFosterMoney(Integer fosterMoney) {
        this.fosterMoney = fosterMoney;
    }

    public String getFosterInfo() {
        return fosterInfo;
    }

    public void setFosterInfo(String fosterInfo) {
        this.fosterInfo = fosterInfo;
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
        return "Foster{" +
            "fosterId = " + fosterId +
            ", fosterCode = " + fosterCode +
            ", petId = " + petId +
            ", clientId = " + clientId +
            ", fosterTerm = " + fosterTerm +
            ", fosterMoney = " + fosterMoney +
            ", fosterInfo = " + fosterInfo +
            ", createTime = " + createTime +
            ", updateTime = " + updateTime +
            ", isDel = " + isDel +
        "}";
    }
}
