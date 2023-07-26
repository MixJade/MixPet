package com.ship.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ship.dto.AppointmentDto;
import com.ship.dto.AppointmentDto2;
import com.ship.entity.Appointment;
import com.ship.mapper.AppointmentMapper;
import com.ship.service.IAppointmentService;
import org.springframework.stereotype.Service;

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
        return this.lambdaUpdate()
                .eq(Appointment::getAppointmentId, appointmentId)
                .set(Appointment::getIsDel, String.valueOf(System.currentTimeMillis()))
                .update();
    }

    @Override
    public boolean deleteByIds(List<Long> idGroup) {
        return this.lambdaUpdate()
                .in(Appointment::getAppointmentId, idGroup)
                .set(Appointment::getIsDel, String.valueOf(System.currentTimeMillis()))
                .update();
    }

    @Override
    public IPage<AppointmentDto> selectByPage(String seaName, int seaType, int numPage, int pageSize) {
        return baseMapper.selectAppointmentPage(new Page<>(numPage, pageSize), seaName, seaType);
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
