package com.forge.controller;

import com.forge.common.Result;
import com.forge.entity.Employee;
import com.forge.service.IEmployeeService;
import com.forge.vo.EmployeePutVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 管理员：已经登录
 */
@RestController
@RequestMapping("/adminLog")
public class EmployeeLogController {
    private final IEmployeeService employeeService;

    @Autowired
    public EmployeeLogController(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * 登录成功的管理员信息
     *
     * @return id，账号，名字，电话，照片
     */
    @GetMapping
    public Employee getAdmin() {
        if (SecurityUtils.getSubject().getPrincipal() instanceof Employee employee)
            return employeeService.getById(employee.getEmployeeId());
        return null;
    }

    /**
     * 管理员修改自己的密码
     */
    @PutMapping
    public Result updatePwd(@RequestBody EmployeePutVo putVo) {
        if (SecurityUtils.getSubject().getPrincipal() instanceof Employee employee){
            String pwd = putVo.employeePassword();
            if (pwd == null || pwd.isBlank()) return Result.error("密码不能为空");
            Md5Hash md5Hash = new Md5Hash(pwd, "pet");
            employee.setEmployeePassword(md5Hash.toHex());
            String tel=putVo.employeeTel();
            String photo=putVo.employeePhoto();
            if (!tel.isBlank()) employee.setEmployeeTel(tel);
            if (!photo.isBlank()) employee.setEmployeePhoto(photo);
            return Result.choice("修改",employeeService.updateById(employee));
        }else return Result.error("登录信息过时");
    }
}
