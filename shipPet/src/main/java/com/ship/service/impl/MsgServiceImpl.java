package com.ship.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ship.entity.Msg;
import com.ship.mapper.MsgMapper;
import com.ship.service.IMsgService;
import com.ship.vo.MsgNameVo;
import com.ship.vo.MsgVo;
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
    public Long getByClient(Long clientId) {
        return baseMapper.getByClient(clientId);
    }

    @Override
    public List<MsgVo> getADoctor(Long clientId, Long doctorId) {
        return baseMapper.getADoctor(clientId, doctorId);
    }

    @Override
    public List<MsgNameVo> getDoctorGroup(Long clientId, Long doctorId) {
        return baseMapper.getDoctorGroup(clientId, doctorId);
    }

    @Override
    public Long getByDoctor(Long doctorId) {
        return baseMapper.getByDoctor(doctorId);
    }

    @Override
    public List<MsgVo> getAClient(Long doctorId, Long clientId) {
        return baseMapper.getAClient(doctorId, clientId);
    }

    @Override
    public List<MsgNameVo> getClientGroup(Long doctorId, Long clientId) {
        return baseMapper.getClientGroup(doctorId, clientId);
    }
}
