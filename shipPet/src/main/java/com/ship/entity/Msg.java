package com.ship.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 消息表
 * </p>
 *
 * @author MixJade
 * @since 2023-07-27
 */
public class Msg implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 记录ID
     */
    @TableId(value = "msg_id", type = IdType.AUTO)
    private Integer msgId;

    /**
     * 用户ID
     */
    private Integer clientId;

    /**
     * 医生ID
     */
    private Integer doctorId;

    /**
     * 1为用户发送，0为医生
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
    private Long isDel;

    public Integer getMsgId() {
        return msgId;
    }

    public void setMsgId(Integer msgId) {
        this.msgId = msgId;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
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

    public Long getIsDel() {
        return isDel;
    }

    public void setIsDel(Long isDel) {
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
