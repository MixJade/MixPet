package com.ship.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ship.common.PhotoEnum;
import com.ship.common.Result;
import com.ship.model.entity.Employee;
import com.ship.security.RoleConst;
import com.ship.service.IEmployeeService;
import com.ship.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
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
    public IPage<Employee> getPage(int numPage, int pageSize, String employeeName) {
        return employeeService.selectByPage(employeeName, numPage, pageSize);
    }

    @PostMapping
    @Secured(RoleConst.ADMIN)
    public Result save(@RequestBody Employee em) {
        if (StrUtil.isWhite(em.getEmployeePhoto())) em.setEmployeePhoto(PhotoEnum.CLIENT.getPhotoName());
        if (StrUtil.isWhite(em.getEmployeePassword())) em.setEmployeePassword("123456");
        em.setEmployeePassword(StrUtil.tranPwd(em.getEmployeePassword()));
        return Result.choice("添加", employeeService.save(em));
    }

    @DeleteMapping("/{id}")
    @Secured(RoleConst.ADMIN)
    public Result delete(@PathVariable Integer id) {
        return Result.choice("删除单个", employeeService.deleteById(id));
    }

    @DeleteMapping("/batch/{ids}")
    @Secured(RoleConst.ADMIN)
    public Result deleteGroup(@PathVariable List<Integer> ids) {
        return Result.choice("删除多个", employeeService.deleteByIds(ids));
    }

    @PutMapping
    @Secured(RoleConst.ADMIN)
    public Result update(@RequestBody Employee employee) {
        return Result.choice("修改", employeeService.updateById(employee));
    }
}
