package com.ship.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ship.entity.Msg;
import com.ship.vo.MsgNameVo;
import com.ship.vo.MsgVo;

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
    Long getByClient(Long clientId);

    /**
     * 查询特定用户与医生对话
     */
    List<MsgVo> getADoctor(Long clientId, Long doctorId);

    /**
     * 用户谈过话的医生
     */
    List<MsgNameVo> getDoctorGroup(Long clientId, Long doctorId);


    /**
     * 查询医生最近谈话的用户
     */
    Long getByDoctor(Long doctorId);

    /**
     * 查询特定医生与用户对话
     */
    List<MsgVo> getAClient(Long doctorId, Long clientId);

    /**
     * 医生谈过话的用户
     */
    List<MsgNameVo> getClientGroup(Long doctorId, Long clientId);
}
