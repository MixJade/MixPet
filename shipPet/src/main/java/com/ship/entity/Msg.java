package com.ship.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author MixJade
 * @since 2023-03-09
 */
public class Msg implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 记录ID
     */
    @TableId(value = "msg_id", type = IdType.AUTO)
    private Long msgId;

    /**
     * 客户ID
     */
    private Long clientId;

    /**
     * 医生ID
     */
    private Long doctorId;

    /**
     * 1为客户发送，0为医生
     */
    private Boolean isClient;

    /**
     * 消息内容
     */
    private String msgContent;

    /**
     * 是否为图片消息
     */
    private Boolean isImg;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 逻辑删除，默认0，填充删除日期
     */
    private String isDel;

    public Long getMsgId() {
        return msgId;
    }

    public void setMsgId(Long msgId) {
        this.msgId = msgId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public Boolean getIsClient() {
        return isClient;
    }

    public void setIsClient(Boolean isClient) {
        this.isClient = isClient;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public Boolean getIsImg() {
        return isImg;
    }

    public void setIsImg(Boolean isImg) {
        this.isImg = isImg;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getIsDel() {
        return isDel;
    }

    public void setIsDel(String isDel) {
        this.isDel = isDel;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "msgId = " + msgId +
                ", clientId = " + clientId +
                ", doctorId = " + doctorId +
                ", isClient = " + isClient +
                ", msgContent = " + msgContent +
                ", isImg = " + isImg +
                ", updateTime = " + updateTime +
                ", createTime = " + createTime +
                ", isDel = " + isDel +
                "}";
    }
}
