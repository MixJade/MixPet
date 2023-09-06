package com.ship.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ship.mapper.MsgMapper;
import com.ship.model.dto.MsgDo;
import com.ship.model.dto.MsgNameDo;
import com.ship.model.dto.NameDo;
import com.ship.model.entity.Msg;
import com.ship.service.IMsgService;
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
    public NameDo clientGetNearDoctor(Integer clientId) {
        NameDo nameDo = baseMapper.clientGetNearDoctor(clientId);
        if (nameDo != null) return nameDo;
        else return new NameDo(1, "三月七");
    }

    @Override
    public List<MsgDo> clientGetADoctor(Integer clientId, Integer doctorId) {
        return baseMapper.clientGetADoctor(clientId, doctorId);
    }

    @Override
    public List<MsgNameDo> clientGetDoctorGroup(Integer clientId, Integer doctorId) {
        return baseMapper.clientGetDoctorGroup(clientId, doctorId);
    }

    @Override
    public NameDo doctorGetNearClient(Integer doctorId) {
        NameDo nameDo = baseMapper.doctorGetNearClient(doctorId);
        if (nameDo != null) return nameDo;
        else return new NameDo(1, "七月三");
    }

    @Override
    public List<MsgDo> doctorGetAClient(Integer doctorId, Integer clientId) {
        return baseMapper.doctorGetAClient(doctorId, clientId);
    }

    @Override
    public List<MsgNameDo> doctorGetClientGroup(Integer doctorId, Integer clientId) {
        return baseMapper.doctorGetClientGroup(doctorId, clientId);
    }
}
