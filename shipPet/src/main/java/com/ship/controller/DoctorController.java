package com.ship.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ship.common.Result;
import com.ship.model.dto.DoctorDto;
import com.ship.model.entity.Doctor;
import com.ship.model.vo.NameVo;
import com.ship.security.model.RoleConst;
import com.ship.service.IDoctorService;
import com.ship.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
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
     * 管理员：在添加科室主任时，查询医生姓名
     *
     * @return 医生姓名与id
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
    public List<NameVo> getByDepartment(Integer departmentId) {
        return doctorService.selectNameByDepartment(departmentId);
    }

    /**
     * 科室管理：查看一个科室下面的所有医生
     *
     * @param departmentId 科室id
     * @return 对应科室的所有医生信息
     */
    @GetMapping("/department/detail")
    public List<Doctor> getByDepartmentId(Integer departmentId) {
        return doctorService.selectByDepartmentId(departmentId);
    }

    @GetMapping("/page")
    public IPage<DoctorDto> getPage(int numPage, int pageSize, String doctorName, String departmentName) {
        return doctorService.selectByPage(doctorName, departmentName, numPage, pageSize);
    }

    /**
     * 根据id查询单个医生
     *
     * @param doctorId 网页读取get参数，必须用包装类，因为可能不是数字
     * @return 医生信息
     */
    @GetMapping("/one")
    public DoctorDto getOne(Integer doctorId) {
        return doctorService.selectById(doctorId);
    }

    @PostMapping
    @Secured(RoleConst.ADMIN)
    public Result save(@RequestBody Doctor doctor) {
        if (StrUtil.isWhite(doctor.getUsername())) return Result.error("帐号不能为空");
        if (StrUtil.isWhite(doctor.getDoctorName())) return Result.error("姓名不能为空");
        return Result.choice("添加", doctorService.addDoctor(doctor));
    }

    @DeleteMapping("/{id}")
    @Secured(RoleConst.ADMIN)
    public Result delete(@PathVariable Integer id) {
        return Result.choice("删除单个", doctorService.deleteById(id));
    }

    @DeleteMapping("/batch/{ids}")
    @Secured(RoleConst.ADMIN)
    public Result deleteGroup(@PathVariable List<Integer> ids) {
        return Result.choice("删除多个", doctorService.deleteByIds(ids));
    }

    @PutMapping
    @Secured(RoleConst.ADMIN)
    public Result update(@RequestBody Doctor doctor) {
        if (StrUtil.isWhite(doctor.getDoctorName())) return Result.error("姓名不能为空");
        if (doctor.getAuthLv() == null) doctor.setAuthLv(0);
        return Result.choice("修改", doctorService.updateById(doctor));
    }
}
