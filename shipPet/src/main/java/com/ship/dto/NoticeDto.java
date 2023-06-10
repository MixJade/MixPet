package com.ship.dto;

import com.ship.entity.Notice;

public class NoticeDto extends Notice {
    /**
     * 创建的员工姓名或账号
     */
    private String createName;
    /**
     * 修改的员工姓名或账号
     */
    private String updateName;
    /**
     * 公告文本内容
     */
    private String textNotice;

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public String getTextNotice() {
        return textNotice;
    }

    public void setTextNotice(String textNotice) {
        this.textNotice = textNotice;
    }
}
