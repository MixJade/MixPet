package com.forge.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.forge.common.PageUntil;
import com.forge.dto.AppointmentDto;
import com.forge.dto.AppointmentDto2;
import com.forge.entity.Appointment;
import com.forge.mapper.AppointmentMapper;
import com.forge.service.IAppointmentService;
import com.forge.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 挂号单表 服务实现类
 * </p>
 *
 * @author MixJade
 * @since 2022-12-22
 */
@Service
public class AppointmentServiceImpl extends ServiceImpl<AppointmentMapper, Appointment> implements IAppointmentService {

    private final AppointmentMapper appointmentMapper;

    @Autowired
    public AppointmentServiceImpl(AppointmentMapper appointmentMapper) {
        this.appointmentMapper = appointmentMapper;
    }

    @Override
    public boolean deleteById(long appointmentId) {
        String delDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        return appointmentMapper.deleteId(delDate, appointmentId);
    }

    @Override
    public boolean deleteByIds(long[] idGroup) {
        String delDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        return appointmentMapper.deleteIdGroup(delDate, idGroup);
    }

    @Override
    public Page<AppointmentDto> selectByPage(String seaName, int seaType, int numPage, int pageSize) {
        if (seaName != null && !seaName.equals("")) seaName = "%" + seaName + "%";
        int maxCount = appointmentMapper.selectAppointmentCount(seaName, seaType);
        PageUntil pu = PageUntil.pu(numPage, pageSize, maxCount);
        var appointmentList = appointmentMapper.selectAppointmentPage(seaName, seaType, pu);
        return new Page<>(appointmentList, maxCount);
    }

    @Override
    public List<AppointmentDto> getDoctor(long doctorId) {
        return appointmentMapper.getDoctor(doctorId);
    }

    @Override
    public List<AppointmentDto> getClient(long clientId) {
        return appointmentMapper.getClient(clientId);
    }

    @Override
    public List<AppointmentDto2> getDoctorLog(Long doctorId) {
        return appointmentMapper.getDoctorLog(doctorId);
    }
}
