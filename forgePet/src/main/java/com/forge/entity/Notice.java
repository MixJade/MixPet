package com.forge.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 公告表
 * </p>
 *
 * @author MixJade
 * @since 2023-01-18
 */
public class Notice implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 公告id
     */
    @TableId(value = "notice_id", type = IdType.AUTO)
    private Long noticeId;

    /**
     * 公告标题
     */
    private String noticeTitle;

    /**
     * 文本文件名称
     */
    private String noticeFile;

    /**
     * 创建人id
     */
    private Long creatId;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 修改人id
     */
    private Long updateId;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 是否禁用
     */
    private Boolean isDisable;

    /**
     * 逻辑删除，默认0，填充删除日期
     */
    private String isDel;

    public Long getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Long noticeId) {
        this.noticeId = noticeId;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getNoticeFile() {
        return noticeFile;
    }

    public void setNoticeFile(String noticeFile) {
        this.noticeFile = noticeFile;
    }

    public Long getCreatId() {
        return creatId;
    }

    public void setCreatId(Long creatId) {
        this.creatId = creatId;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Long updateId) {
        this.updateId = updateId;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getIsDisable() {
        return isDisable;
    }

    public void setIsDisable(Boolean isDisable) {
        this.isDisable = isDisable;
    }

    public String getIsDel() {
        return isDel;
    }

    public void setIsDel(String isDel) {
        this.isDel = isDel;
    }

    @Override
    public String toString() {
        return "Notice{" +
            "noticeId = " + noticeId +
            ", noticeTitle = " + noticeTitle +
            ", noticeFile = " + noticeFile +
            ", creatId = " + creatId +
            ", createTime = " + createTime +
            ", updateId = " + updateId +
            ", updateTime = " + updateTime +
            ", isDisable = " + isDisable +
            ", isDel = " + isDel +
        "}";
    }
}
