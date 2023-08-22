package com.ship.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.io.Serial;

/**
 * <p>
 * 员工表
 * </p>
 *
 * @author MixJade
 * @since 2023-08-22
 */
public class Employee implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 管理员id
     */
    @TableId(value = "employee_id", type = IdType.AUTO)
    private Integer employeeId;

    /**
     * 管理员账号
     */
    private String employeeUsername;

    /**
     * 管理员密码
     */
    private String employeePassword;

    /**
     * 管理员姓名
     */
    private String employeeName;

    /**
     * 管理员等级
     */
    private Integer employeeLevel;

    /**
     * 管理员联系方式
     */
    private String employeeTel;

    /**
     * 管理员照片名称
     */
    private String employeePhoto;

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

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeUsername() {
        return employeeUsername;
    }

    public void setEmployeeUsername(String employeeUsername) {
        this.employeeUsername = employeeUsername;
    }

    public String getEmployeePassword() {
        return employeePassword;
    }

    public void setEmployeePassword(String employeePassword) {
        this.employeePassword = employeePassword;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Integer getEmployeeLevel() {
        return employeeLevel;
    }

    public void setEmployeeLevel(Integer employeeLevel) {
        this.employeeLevel = employeeLevel;
    }

    public String getEmployeeTel() {
        return employeeTel;
    }

    public void setEmployeeTel(String employeeTel) {
        this.employeeTel = employeeTel;
    }

    public String getEmployeePhoto() {
        return employeePhoto;
    }

    public void setEmployeePhoto(String employeePhoto) {
        this.employeePhoto = employeePhoto;
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
        return "Employee{" +
                "employeeId = " + employeeId +
                ", employeeUsername = " + employeeUsername +
                ", employeePassword = " + employeePassword +
                ", employeeName = " + employeeName +
                ", employeeLevel = " + employeeLevel +
                ", employeeTel = " + employeeTel +
                ", employeePhoto = " + employeePhoto +
                ", createTime = " + createTime +
                ", updateTime = " + updateTime +
                ", isDel = " + isDel +
                "}";
    }
}
