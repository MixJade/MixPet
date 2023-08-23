package com.ship.controller;

import com.ship.common.Result;
import com.ship.common.SendMail;
import com.ship.model.dto.AppointmentDto2;
import com.ship.model.dto.DoctorDto;
import com.ship.model.entity.Appointment;
import com.ship.model.entity.Doctor;
import com.ship.model.vo.DoctorResetPwdVo;
import com.ship.security.model.RoleConst;
import com.ship.service.IAppointmentService;
import com.ship.service.IDoctorService;
import com.ship.util.StrUtil;
import com.ship.util.UserUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 医生：已经登录
 */
@RestController
@RequestMapping("/doctorLog")
@Secured(RoleConst.LOSER)
public class DoctorLogController {
    private final IDoctorService doctorService;
    private final IAppointmentService appointService;
    private final SendMail sendMail;

    @Autowired
    public DoctorLogController(IDoctorService doctorService, IAppointmentService appointService, SendMail sendMail) {
        this.doctorService = doctorService;
        this.appointService = appointService;
        this.sendMail = sendMail;
    }

    /**
     * 医生：查询当前登录的医生信息
     */
    @GetMapping
    public DoctorDto getOneLogin() {
        if (UserUtil.getUser() instanceof Doctor doctor) {
            return doctorService.selectById(doctor.getDoctorId());
        } else return null;
    }

    /**
     * 医生：查询自己的订单
     */
    @GetMapping("/appoint")
    public List<AppointmentDto2> getAppoint() {
        if (UserUtil.getUser() instanceof Doctor doctor) {
            return appointService.getDoctorLog(doctor.getDoctorId());
        } else return null;
    }

    /**
     * 医生：重置密码发送邮件
     */
    @GetMapping("/resetPwd")
    public Result sendMail(HttpSession session) {
        if (UserUtil.getUser() instanceof Doctor doctor) {
            String mail = doctor.getDoctorTel();
            String code = sendMail.sendQQEmail(mail);
            if (code != null) session.setAttribute(mail, code);
            return Result.success("验证码已发送至邮箱");
        } else return Result.error("发送失败");
    }

    /**
     * 医生：重置密码
     */
    @PutMapping("/resetPwd")
    public Result resetPwd(@RequestBody DoctorResetPwdVo pwdVo, HttpSession session) {
        String password = pwdVo.password();
        String checkCode = pwdVo.checkCode();
        if (UserUtil.getUser() instanceof Doctor doctor) {
            String mail = doctor.getDoctorTel();
            var sessionCode = session.getAttribute(mail);
            if (StrUtil.isWhite(checkCode)) return Result.error("验证码不能为空");
            if (StrUtil.isWhite(password)) return Result.error("密码不能为框");
            else if (sessionCode == null) return Result.error("请点击发送验证码");
            else if (sessionCode.equals(checkCode.toUpperCase())) {
                session.invalidate();//销毁验证码
                password = StrUtil.tranPwd(password);
                doctor.setDoctorPassword(password);// 重新设置密码
                return Result.choice("密码重置", doctorService.updateById(doctor));
            } else return Result.error("验证码不正确");
        } else return Result.error("当前未登录");
    }

    /**
     * 医生：修改简历
     */
    @PutMapping
    public Result update(@RequestBody Doctor doctor) {
        if (UserUtil.getUser() instanceof Doctor doctorLog) {
            doctor.setDoctorId(doctorLog.getDoctorId());
            return Result.choice("修改", doctorService.updateById(doctor));
        } else return null;
    }

    /**
     * 医生：添加挂号
     */
    @PostMapping("/appoint")
    public Result addAppoint(@RequestBody Appointment appointment) {
        if (UserUtil.getUser() instanceof Doctor doctorLog) {
            appointment.setDoctorId(doctorLog.getDoctorId());
            appointment.setDepartmentId(doctorLog.getDepartmentId());
            return Result.choice("添加", appointService.save(appointment));
        } else return Result.error("未获取登录信息");
    }

    /**
     * 医生：修改挂号
     */
    @PutMapping("/appoint")
    public Result upAppoint(@RequestBody Appointment appointment) {
        if (UserUtil.getUser() instanceof Doctor doctorLog) {
            appointment.setDoctorId(doctorLog.getDoctorId());
            appointment.setDepartmentId(doctorLog.getDepartmentId());
            return Result.choice("修改", appointService.updateById(appointment));
        } else return Result.error("未获取登录信息");
    }
}
