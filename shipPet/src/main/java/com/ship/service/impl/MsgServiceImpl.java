package com.ship.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ship.model.entity.Msg;
import com.ship.mapper.MsgMapper;
import com.ship.service.IMsgService;
import com.ship.model.dto.MsgNameDo;
import com.ship.model.dto.MsgDo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 聊天记录 服务实现类
 * </p>
 *
 * @author MixJade
 * @since 2023-02-25
 */
@Service
public class MsgServiceImpl extends ServiceImpl<MsgMapper, Msg> implements IMsgService {

    @Override
    public Integer getByClient(Integer clientId) {
        return baseMapper.getByClient(clientId);
    }

    @Override
    public List<MsgDo> getADoctor(Integer clientId, Integer doctorId) {
        return baseMapper.getADoctor(clientId, doctorId);
    }

    @Override
    public List<MsgNameDo> getDoctorGroup(Integer clientId, Integer doctorId) {
        return baseMapper.getDoctorGroup(clientId, doctorId);
    }

    @Override
    public Integer getByDoctor(Integer doctorId) {
        return baseMapper.getByDoctor(doctorId);
    }

    @Override
    public List<MsgDo> getAClient(Integer doctorId, Integer clientId) {
        return baseMapper.getAClient(doctorId, clientId);
    }

    @Override
    public List<MsgNameDo> getClientGroup(Integer doctorId, Integer clientId) {
        return baseMapper.getClientGroup(doctorId, clientId);
    }
}
