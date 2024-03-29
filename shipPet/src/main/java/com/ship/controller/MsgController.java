package com.ship.controller;

import com.ship.common.Result;
import com.ship.model.entity.Client;
import com.ship.model.entity.Doctor;
import com.ship.model.entity.Msg;
import com.ship.model.dto.MsgNameDo;
import com.ship.model.dto.MsgDo;
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
    @GetMapping
    public Integer getByClient() {
        if (UserUtil.getUser() instanceof Client client)
            return msgService.getByClient(client.getClientId());
        return null;
    }

    /**
     * 用户：查询特定用户与医生对话
     */
    @GetMapping("/aDoctor")
    public List<MsgDo> getADoctor(Integer doctorId) {
        if (UserUtil.getUser() instanceof Client client)
            return msgService.getADoctor(client.getClientId(), doctorId);
        return null;
    }

    /**
     * 用户：查询用户谈过话的医生
     */
    @GetMapping("/group")
    public List<MsgNameDo> getDoctorGroup(Integer doctorId) {
        if (UserUtil.getUser() instanceof Client client)
            return msgService.getDoctorGroup(client.getClientId(), doctorId);
        return null;
    }

    /**
     * 用户：发送消息
     */
    @PostMapping
    public Result sendMessage(@RequestBody Msg msg) {
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
    @GetMapping("/d")
    public Integer getByDoctor() {
        if (UserUtil.getUser() instanceof Doctor doctor)
            return msgService.getByDoctor(doctor.getDoctorId());
        return null;
    }

    /**
     * 医生：查询特定医生与用户对话
     */
    @GetMapping("/aClient")
    public List<MsgDo> getAClient(Integer clientId) {
        if (UserUtil.getUser() instanceof Doctor doctor)
            return msgService.getAClient(doctor.getDoctorId(), clientId);
        return null;
    }

    /**
     * 医生：医生谈过话的用户
     */
    @GetMapping("/d/group")
    public List<MsgNameDo> getClientGroup(Integer clientId) {
        if (UserUtil.getUser() instanceof Doctor doctor)
            return msgService.getClientGroup(doctor.getDoctorId(), clientId);
        return null;
    }

    /**
     * 医生：发送消息
     */
    @PostMapping("/d")
    public Result sendMessageD(@RequestBody Msg msg) {
        if (UserUtil.getUser() instanceof Doctor doctor) {
            msg.setDoctorId(doctor.getDoctorId());
            msg.setIsClient(false);
            return Result.choice("消息发送", msgService.save(msg));
        }
        return Result.error("未有登录");
    }
}
