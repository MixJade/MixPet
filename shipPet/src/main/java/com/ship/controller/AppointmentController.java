package com.ship.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ship.common.Result;
import com.ship.model.dto.AppointmentDto;
import com.ship.model.entity.Appointment;
import com.ship.model.entity.Client;
import com.ship.security.model.RoleConst;
import com.ship.service.IAppointmentService;
import com.ship.util.StrUtil;
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
    public List<AppointmentDto> getDoctor(Integer doctorId) {
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
        Result checkAppoint = checkAppoint(appointment);
        if (checkAppoint.code() == 0) return checkAppoint;
        else return Result.choice("添加", appointmentService.save(appointment));
    }

    @DeleteMapping("/{id}")
    @Secured(RoleConst.DOCTOR)
    public Result delete(@PathVariable Integer id) {
        return Result.choice("删除单个", appointmentService.deleteById(id));
    }

    @DeleteMapping("/batch/{ids}")
    @Secured(RoleConst.DOCTOR)
    public Result deleteGroup(@PathVariable List<Integer> ids) {
        return Result.choice("删除多个", appointmentService.deleteByIds(ids));
    }

    @PutMapping
    @Secured(RoleConst.DOCTOR)
    public Result update(@RequestBody Appointment appointment) {
        Result checkAppoint = checkAppoint(appointment);
        if (checkAppoint.code() == 0) return checkAppoint;
        else return Result.choice("修改", appointmentService.updateById(appointment));
    }

    /**
     * 新增或修改时，校验信息
     *
     * @param appoint 新增或修改的挂号传参
     * @return 是否校验通过
     */
    private Result checkAppoint(Appointment appoint) {
        if (appoint.getAppointmentDate() == null)
            return Result.error("就诊时间不能为空");
        if (StrUtil.isWhite(appoint.getAppointmentInfo()))
            return Result.error("请输入挂号描述");
        if (appoint.getClientId() == null || appoint.getClientId() == 0)
            return Result.error("请选择用户");
        if (appoint.getPetId() == null || appoint.getPetId() == 0)
            return Result.error("请选择宠物");
        if (appoint.getDepartmentId() == null || appoint.getDepartmentId() == 0)
            return Result.error("请选择科室");
        if (appoint.getDoctorId() == null || appoint.getDoctorId() == 0)
            return Result.error("请选择医生");
        else return Result.success("数据合法");
    }
}
