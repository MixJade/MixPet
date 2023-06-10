package com.ship.controller;

import com.ship.common.PhotoConst;
import com.ship.common.Result;
import com.ship.entity.Employee;
import com.ship.security.RoleConst;
import com.ship.service.IEmployeeService;
import com.ship.util.StrUtil;
import com.ship.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

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
    @Secured(RoleConst.ADMIN)
    public Result save(@RequestBody Employee employee) {
        if (StrUtil.isWhite(employee.getEmployeePhoto())) employee.setEmployeePhoto(PhotoConst.CLIENT);
        return Result.choice("添加", employeeService.save(employee));
    }

    @DeleteMapping("/{id}")
    @Secured(RoleConst.ADMIN)
    public Result delete(@PathVariable Long id) {
        return Result.choice("删除单个", employeeService.deleteById(id));
    }

    @PutMapping
    @Secured(RoleConst.ADMIN)
    public Result update(@RequestBody Employee employee) {
        return Result.choice("修改", employeeService.updateById(employee));
    }
}
