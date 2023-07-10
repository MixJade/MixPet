package com.ship.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ship.common.Result;
import com.ship.entity.Department;
import com.ship.security.RoleConst;
import com.ship.service.IDepartmentService;
import com.ship.vo.NameVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 部门表 前端控制器
 * </p>
 *
 * @author MixJade
 * @since 2022-12-22
 */
@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final IDepartmentService departService;

    @Autowired
    public DepartmentController(IDepartmentService departService) {
        this.departService = departService;
    }

    /**
     * 查询科室名与对应id
     *
     * @return 相应科室名与id
     */
    @GetMapping
    public List<NameVo> getName() {
        return departService.selectName();
    }

    @GetMapping("/page")
    public IPage<Department> getPage(int numPage, int pageSize, String departmentName) {
        return departService.selectByPage(departmentName, numPage, pageSize);
    }

    @PostMapping
    @Secured(RoleConst.ADMIN)
    public Result save(@RequestBody Department department) {
        return Result.choice("添加", departService.save(department));
    }

    @DeleteMapping("/{id}")
    @Secured(RoleConst.ADMIN)
    public Result delete(@PathVariable Long id) {
        return Result.choice("删除单个", departService.deleteById(id));
    }

    @PutMapping
    @Secured(RoleConst.ADMIN)
    public Result update(@RequestBody Department department) {
        return Result.choice("修改", departService.updateById(department));
    }

}
