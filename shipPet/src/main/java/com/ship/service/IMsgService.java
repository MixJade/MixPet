package com.ship.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ship.model.entity.Msg;
import com.ship.model.dto.MsgNameDo;
import com.ship.model.dto.MsgDo;

import java.util.List;

/**
 * <p>
 * 聊天记录 服务类
 * </p>
 *
 * @author MixJade
 * @since 2023-02-25
 */
public interface IMsgService extends IService<Msg> {
    /**
     * 查询用户最近谈话的医生
     */
    Integer getByClient(Integer clientId);

    /**
     * 查询特定用户与医生对话
     */
    List<MsgDo> getADoctor(Integer clientId, Integer doctorId);

    /**
     * 用户谈过话的医生
     */
    List<MsgNameDo> getDoctorGroup(Integer clientId, Integer doctorId);


    /**
     * 查询医生最近谈话的用户
     */
    Integer getByDoctor(Integer doctorId);

    /**
     * 查询特定医生与用户对话
     */
    List<MsgDo> getAClient(Integer doctorId, Integer clientId);

    /**
     * 医生谈过话的用户
     */
    List<MsgNameDo> getClientGroup(Integer doctorId, Integer clientId);
}
