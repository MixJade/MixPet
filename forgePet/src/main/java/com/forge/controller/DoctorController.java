package com.forge.controller;

import com.forge.common.Result;
import com.forge.util.StrUtil;
import com.forge.dto.DoctorDto;
import com.forge.entity.Doctor;
import com.forge.service.IDoctorService;
import com.forge.shiro.RoleConst;
import com.forge.vo.NameVo;
import com.forge.vo.Page;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 医生表，外键部门 前端控制器
 * </p>
 *
 * @author MixJade
 * @since 2022-12-22
 */
@RestController
@RequestMapping("/doctor")
public class DoctorController {
    private final IDoctorService doctorService;

    @Autowired
    public DoctorController(IDoctorService doctorService) {
        this.doctorService = doctorService;
    }

    /**
     * 调试所用，实际未调用
     */
    @GetMapping
    public List<NameVo> getName() {
        return doctorService.selectName();
    }

    /**
     * 管理员：在添加挂号单时，通过科室查询医生
     *
     * @param departmentId 科室id
     * @return 医生姓名与id
     */
    @GetMapping("/department")
    public List<NameVo> getByDepartment(long departmentId) {
        return doctorService.selectByDepartment(departmentId);
    }

    @GetMapping("/page")
    public Page<DoctorDto> getPage(int numPage, int pageSize, String doctorName, String departmentName) {
        return doctorService.selectByPage(doctorName, departmentName, numPage, pageSize);
    }

    /**
     * 根据id查询单个医生
     *
     * @param doctorId 网页读取get参数，必须用包装类，因为可能不是数字
     * @return 医生信息
     */
    @GetMapping("/one")
    public DoctorDto getOne(Long doctorId) {
        return doctorService.selectById(doctorId);
    }

    @PostMapping
    @RequiresRoles(RoleConst.MANAGER)
    public Result save(@RequestBody Doctor doctor) {
        if (StrUtil.isWhite(doctor.getDoctorJob())) doctor.setDoctorJob("医生");
        return Result.choice("添加", doctorService.save(doctor));
    }

    @DeleteMapping("/{id}")
    @RequiresRoles(RoleConst.MANAGER)
    public Result delete(@PathVariable Long id) {
        return Result.choice("删除单个", doctorService.deleteById(id));
    }

    @DeleteMapping("/batch/{ids}")
    @RequiresRoles(RoleConst.MANAGER)
    public Result deleteGroup(@PathVariable long[] ids) {
        return Result.choice("删除多个", doctorService.deleteByIds(ids));
    }

    @PutMapping
    @RequiresRoles(RoleConst.MANAGER)
    public Result update(@RequestBody Doctor doctor) {
        return Result.choice("修改", doctorService.updateById(doctor));
    }
}
