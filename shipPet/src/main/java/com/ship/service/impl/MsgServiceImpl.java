package com.ship.service.impl;

import com.ship.dto.MsgDto;
import com.ship.entity.Msg;
import com.ship.mapper.MsgMapper;
import com.ship.service.IMsgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ship.vo.MsgClientNameVo;
import com.ship.vo.MsgDoctorNameVo;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final MsgMapper msgMapper;

    @Autowired
    public MsgServiceImpl(MsgMapper msgMapper) {
        this.msgMapper = msgMapper;
    }

    @Override
    public Long getByClient(Long clientId) {
        return msgMapper.getByClient(clientId);
    }

    @Override
    public List<MsgDto> getADoctor(Long clientId, Long doctorId) {
        return msgMapper.getADoctor(clientId, doctorId);
    }

    @Override
    public List<MsgDoctorNameVo> getDoctorGroup(Long clientId, Long doctorId) {
        return msgMapper.getDoctorGroup(clientId, doctorId);
    }

    @Override
    public Long getByDoctor(Long doctorId) {
        return msgMapper.getByDoctor(doctorId);
    }

    @Override
    public List<MsgDto> getAClient(Long doctorId, Long clientId) {
        return msgMapper.getAClient(doctorId,clientId);
    }

    @Override
    public List<MsgClientNameVo> getClientGroup(Long doctorId, Long clientId) {
        return msgMapper.getClientGroup(doctorId,clientId);
    }
}
