package com.ship.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ship.common.Result;
import com.ship.model.dto.DepartmentDto;
import com.ship.model.entity.Department;
import com.ship.model.vo.NameVo;
import com.ship.security.model.RoleConst;
import com.ship.service.IDepartmentService;
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

    /**
     * 【内联查询】名下有医生的科室，带上当前科室
     *
     * @param departmentId 当前科室ID
     * @return 科室名称+ID
     */
    @GetMapping("/full")
    public List<NameVo> getNameHaveDoctor(Integer departmentId) {
        return departService.selectNameHaveDoctor(departmentId);
    }

    @GetMapping("/page")
    public IPage<DepartmentDto> getPage(int numPage, int pageSize, String departmentName) {
        return departService.selectByPage(departmentName, numPage, pageSize);
    }

    @PostMapping
    @Secured(RoleConst.ADMIN)
    public Result save(@RequestBody Department department) {
        return Result.choice("添加", departService.save(department));
    }

    @DeleteMapping("/{id}")
    @Secured(RoleConst.ADMIN)
    public Result delete(@PathVariable Integer id) {
        return departService.deleteById(id);
    }

    @DeleteMapping("/batch/{ids}")
    @Secured(RoleConst.MANAGER)
    public Result deleteGroup(@PathVariable List<Integer> ids) {
        return departService.deleteByIds(ids);
    }

    @PutMapping
    @Secured(RoleConst.ADMIN)
    public Result update(@RequestBody Department department) {
        return Result.choice("修改", departService.updateDepart(department));
    }

}
