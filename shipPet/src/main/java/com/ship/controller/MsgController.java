package com.ship.controller;

import com.ship.common.Result;
import com.ship.model.dto.MsgDo;
import com.ship.model.dto.MsgNameDo;
import com.ship.model.dto.NameDo;
import com.ship.model.entity.Client;
import com.ship.model.entity.Doctor;
import com.ship.model.entity.Msg;
import com.ship.service.IMsgService;
import com.ship.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 聊天记录 前端控制器
 * </p>
 *
 * @author MixJade
 * @since 2023-02-25
 */
@RestController
@RequestMapping("/msg")
public class MsgController {
    private final IMsgService msgService;

    @Autowired
    public MsgController(IMsgService msgService) {
        this.msgService = msgService;
    }

    /**
     * 用户：查询用户最近谈话的医生
     */
    @GetMapping("/client/nearDoctor")
    public NameDo clientGetNearDoctor() {
        if (UserUtil.getUser() instanceof Client client)
            return msgService.clientGetNearDoctor(client.getClientId());
        return null;
    }

    /**
     * 用户：查询特定用户与医生对话
     */
    @GetMapping("/client/aDoctor")
    public List<MsgDo> clientGetADoctor(Integer doctorId) {
        if (UserUtil.getUser() instanceof Client client)
            return msgService.clientGetADoctor(client.getClientId(), doctorId);
        return null;
    }

    /**
     * 用户：查询用户谈过话的医生
     *
     * @param doctorId 当前的医生，排序第一
     */
    @GetMapping("/client/doctorGroup")
    public List<MsgNameDo> clientGetDoctorGroup(Integer doctorId) {
        if (UserUtil.getUser() instanceof Client client)
            return msgService.clientGetDoctorGroup(client.getClientId(), doctorId);
        return null;
    }

    /**
     * 用户：发送消息
     */
    @PostMapping("/client")
    public Result sendClientMessage(@RequestBody Msg msg) {
        if (UserUtil.getUser() instanceof Client client) {
            msg.setClientId(client.getClientId());
            msg.setIsClient(true);
            return Result.choice("消息发送", msgService.save(msg));
        }
        return Result.error("未有登录");
    }

    /**
     * 医生：查询医生最近谈话的用户
     */
    @GetMapping("/doctor/nearClient")
    public NameDo doctorGetNearClient() {
        if (UserUtil.getUser() instanceof Doctor doctor)
            return msgService.doctorGetNearClient(doctor.getDoctorId());
        return null;
    }

    /**
     * 医生：查询特定医生与用户对话
     */
    @GetMapping("/doctor/aClient")
    public List<MsgDo> doctorGetAClient(Integer clientId) {
        if (UserUtil.getUser() instanceof Doctor doctor)
            return msgService.doctorGetAClient(doctor.getDoctorId(), clientId);
        return null;
    }

    /**
     * 医生：医生谈过话的用户
     */
    @GetMapping("/doctor/clientGroup")
    public List<MsgNameDo> doctorGetClientGroup(Integer clientId) {
        if (UserUtil.getUser() instanceof Doctor doctor)
            return msgService.doctorGetClientGroup(doctor.getDoctorId(), clientId);
        return null;
    }

    /**
     * 医生：发送消息
     */
    @PostMapping("/doctor")
    public Result sendDoctorMessage(@RequestBody Msg msg) {
        if (UserUtil.getUser() instanceof Doctor doctor) {
            msg.setDoctorId(doctor.getDoctorId());
            msg.setIsClient(false);
            return Result.choice("消息发送", msgService.save(msg));
        }
        return Result.error("未有登录");
    }
}
