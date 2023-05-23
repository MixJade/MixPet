package com.forge.controller;

import com.forge.common.Result;
import com.forge.shiro.RoleConst;
import com.forge.vo.Page;
import com.forge.entity.Employee;
import com.forge.service.IEmployeeService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 员工表 前端控制器
 * </p>
 *
 * @author MixJade
 * @since 2022-12-22
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final IEmployeeService employeeService;

    @Autowired
    public EmployeeController(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/page")
    public Page<Employee> getPage(int numPage, int pageSize, String employeeName) {
        return employeeService.selectByPage(employeeName, numPage, pageSize);
    }

    @PostMapping
    @RequiresRoles(RoleConst.ADMIN)
    public Result save(@RequestBody Employee employee) {
        return Result.choice("添加", employeeService.save(employee));
    }

    @DeleteMapping("/{id}")
    @RequiresRoles(RoleConst.ADMIN)
    public Result delete(@PathVariable Long id) {
        return Result.choice("删除单个", employeeService.deleteById(id));
    }

    @PutMapping
    @RequiresRoles(RoleConst.ADMIN)
    public Result update(@RequestBody Employee employee) {
        return Result.choice("修改", employeeService.updateById(employee));
    }
}
