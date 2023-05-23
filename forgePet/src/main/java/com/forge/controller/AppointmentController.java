package com.forge.controller;

import com.forge.common.Result;
import com.forge.dto.AppointmentDto;
import com.forge.entity.Client;
import com.forge.shiro.RoleConst;
import com.forge.vo.Page;
import com.forge.entity.Appointment;
import com.forge.service.IAppointmentService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 挂号单表 前端控制器
 * </p>
 *
 * @author MixJade
 * @since 2022-12-22
 */
@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    private final IAppointmentService appointmentService;

    @Autowired
    public AppointmentController(IAppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }
    /**
     * 调试所用，实际未调用
     */
    @GetMapping
    public List<Appointment> getAll() {
        return appointmentService.list();
    }

    @GetMapping("/page")
    public Page<AppointmentDto> getPage(int numPage, int pageSize, String seaName, int seaType) {
        return appointmentService.selectByPage(seaName, seaType, numPage, pageSize);
    }

    /**
     * 根据医生查询挂号信息
     */
    @GetMapping("/doctor")
    public List<AppointmentDto> getDoctor(Long doctorId) {
        return appointmentService.getDoctor(doctorId);
    }

    /**
     * 用户个人中心:根据用户查询挂号信息
     */
    @GetMapping("/client")
    public List<AppointmentDto> getClient() {
        if (SecurityUtils.getSubject().getPrincipal() instanceof Client client) {
            return appointmentService.getClient(client.getClientId());
        } else return null;
    }

    @PostMapping
    @RequiresRoles(RoleConst.NURSE)
    public Result save(@RequestBody Appointment appointment) {
        return Result.choice("添加", appointmentService.save(appointment));
    }

    @DeleteMapping("/{id}")
    @RequiresRoles(RoleConst.MANAGER)
    public Result delete(@PathVariable Long id) {
        return Result.choice("删除单个", appointmentService.deleteById(id));
    }

    @DeleteMapping("/batch/{ids}")
    @RequiresRoles(RoleConst.MANAGER)
    public Result deleteGroup(@PathVariable long[] ids) {
        return Result.choice("删除多个", appointmentService.deleteByIds(ids));
    }

    @PutMapping
    @RequiresRoles(value = {RoleConst.MANAGER, RoleConst.DOCTOR}, logical = Logical.OR)
    public Result update(@RequestBody Appointment appointment) {
        return Result.choice("修改", appointmentService.updateById(appointment));
    }

}
