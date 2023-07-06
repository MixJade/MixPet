package com.ship.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ship.dto.AppointmentDto;
import com.ship.dto.AppointmentDto2;
import com.ship.entity.Appointment;
import com.ship.mapper.AppointmentMapper;
import com.ship.service.IAppointmentService;
import com.ship.util.PageUtil;
import com.ship.vo.Page;
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
    @Override
    public boolean deleteById(long appointmentId) {
        String delDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        return this.lambdaUpdate()
                .eq(Appointment::getAppointmentId, appointmentId)
                .set(Appointment::getIsDel, delDate)
                .update();
    }

    @Override
    public boolean deleteByIds(List<Long> idGroup) {
        String delDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        return this.lambdaUpdate()
                .in(Appointment::getAppointmentId, idGroup)
                .set(Appointment::getIsDel, delDate)
                .update();
    }

    @Override
    public Page<AppointmentDto> selectByPage(String seaName, int seaType, int numPage, int pageSize) {
        if (seaName != null && !seaName.equals("")) seaName = "%" + seaName + "%";
        int maxCount = baseMapper.selectAppointmentCount(seaName, seaType);
        PageUtil pu = PageUtil.pu(numPage, pageSize, maxCount);
        var appointmentList = baseMapper.selectAppointmentPage(seaName, seaType, pu);
        return new Page<>(appointmentList, maxCount);
    }

    @Override
    public List<AppointmentDto> getDoctor(long doctorId) {
        return baseMapper.getDoctor(doctorId);
    }

    @Override
    public List<AppointmentDto> getClient(long clientId) {
        return baseMapper.getClient(clientId);
    }

    @Override
    public List<AppointmentDto2> getDoctorLog(Long doctorId) {
        return baseMapper.getDoctorLog(doctorId);
    }
}
