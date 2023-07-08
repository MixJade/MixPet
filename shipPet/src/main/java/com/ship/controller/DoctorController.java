package com.ship.controller;

import com.ship.common.PhotoEnum;
import com.ship.common.Result;
import com.ship.dto.DoctorDto;
import com.ship.entity.Doctor;
import com.ship.security.RoleConst;
import com.ship.service.IDoctorService;
import com.ship.util.StrUtil;
import com.ship.vo.NameVo;
import com.ship.vo.Page;
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
    @Secured(RoleConst.MANAGER)
    public Result save(@RequestBody Doctor doctor) {
        if (StrUtil.isWhite(doctor.getDoctorName())) return Result.error("姓名不能为空");
        if (StrUtil.isWhite(doctor.getDoctorJob())) doctor.setDoctorJob("医生");
        if (StrUtil.isWhite(doctor.getDoctorPhoto())) doctor.setDoctorPhoto(PhotoEnum.DOCTOR.getPhotoName());
        return Result.choice("添加", doctorService.save(doctor));
    }

    @DeleteMapping("/{id}")
    @Secured(RoleConst.MANAGER)
    public Result delete(@PathVariable Long id) {
        return Result.choice("删除单个", doctorService.deleteById(id));
    }

    @DeleteMapping("/batch/{ids}")
    @Secured(RoleConst.MANAGER)
    public Result deleteGroup(@PathVariable List<Long> ids) {
        return Result.choice("删除多个", doctorService.deleteByIds(ids));
    }

    @PutMapping
    @Secured(RoleConst.MANAGER)
    public Result update(@RequestBody Doctor doctor) {
        if (StrUtil.isWhite(doctor.getDoctorName())) return Result.error("姓名不能为空");
        if (StrUtil.isWhite(doctor.getDoctorJob())) doctor.setDoctorJob("医生");
        return Result.choice("修改", doctorService.updateById(doctor));
    }
}
