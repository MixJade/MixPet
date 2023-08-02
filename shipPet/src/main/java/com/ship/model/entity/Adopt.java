package com.ship.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 领养宠物订单
 * </p>
 *
 * @author MixJade
 * @since 2023-07-27
 */
public class Adopt implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 领养表的id
     */
    @TableId(value = "adopt_id", type = IdType.AUTO)
    private Integer adoptId;

    /**
     * 订单编号
     */
    private String adoptCode;

    /**
     * 领养宠物的id
     */
    private Integer petId;

    /**
     * 领养人id
     */
    private Integer clientId;

    /**
     * 领养押金
     */
    private Integer adoptMoney;

    /**
     * 订单备注
     */
    private String adoptInfo;

    /**
     * 是否通过，2为待审核，1为通过，0为未通过
     */
    private Byte inAdopt;

    /**
     * 创建时间，也是领养时间
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

    public Integer getAdoptId() {
        return adoptId;
    }

    public void setAdoptId(Integer adoptId) {
        this.adoptId = adoptId;
    }

    public String getAdoptCode() {
        return adoptCode;
    }

    public void setAdoptCode(String adoptCode) {
        this.adoptCode = adoptCode;
    }

    public Integer getPetId() {
        return petId;
    }

    public void setPetId(Integer petId) {
        this.petId = petId;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getAdoptMoney() {
        return adoptMoney;
    }

    public void setAdoptMoney(Integer adoptMoney) {
        this.adoptMoney = adoptMoney;
    }

    public String getAdoptInfo() {
        return adoptInfo;
    }

    public void setAdoptInfo(String adoptInfo) {
        this.adoptInfo = adoptInfo;
    }

    public Byte getInAdopt() {
        return inAdopt;
    }

    public void setInAdopt(Byte inAdopt) {
        this.inAdopt = inAdopt;
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
        return "Adopt{" +
            "adoptId = " + adoptId +
            ", adoptCode = " + adoptCode +
            ", petId = " + petId +
            ", clientId = " + clientId +
            ", adoptMoney = " + adoptMoney +
            ", adoptInfo = " + adoptInfo +
            ", inAdopt = " + inAdopt +
            ", createTime = " + createTime +
            ", updateTime = " + updateTime +
            ", isDel = " + isDel +
        "}";
    }
}
