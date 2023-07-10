package com.ship.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ship.common.Result;
import com.ship.dto.AppointmentDto;
import com.ship.entity.Appointment;
import com.ship.entity.Client;
import com.ship.security.RoleConst;
import com.ship.service.IAppointmentService;
import com.ship.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
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

    @GetMapping("/page")
    public IPage<AppointmentDto> getPage(int numPage, int pageSize, String seaName, int seaType) {
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
        if (UserUtil.getUser() instanceof Client client) {
            return appointmentService.getClient(client.getClientId());
        } else return null;
    }

    @PostMapping
    @Secured(RoleConst.NURSE)
    public Result save(@RequestBody Appointment appointment) {
        return Result.choice("添加", appointmentService.save(appointment));
    }

    @DeleteMapping("/{id}")
    @Secured(RoleConst.MANAGER)
    public Result delete(@PathVariable Long id) {
        return Result.choice("删除单个", appointmentService.deleteById(id));
    }

    @DeleteMapping("/batch/{ids}")
    @Secured(RoleConst.MANAGER)
    public Result deleteGroup(@PathVariable List<Long> ids) {
        return Result.choice("删除多个", appointmentService.deleteByIds(ids));
    }

    @PutMapping
    @Secured({RoleConst.MANAGER, RoleConst.DOCTOR})
    public Result update(@RequestBody Appointment appointment) {
        return Result.choice("修改", appointmentService.updateById(appointment));
    }

}
