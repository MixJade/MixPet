package com.ship.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ship.model.dto.AppointmentDto;
import com.ship.model.dto.AppointmentDto2;
import com.ship.model.entity.Appointment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 挂号单表 Mapper 接口
 * </p>
 *
 * @author MixJade
 * @since 2022-12-22
 */
@Mapper
public interface AppointmentMapper extends BaseMapper<Appointment> {
    IPage<AppointmentDto> selectAppointmentPage(IPage<AppointmentDto> page, String seaName, int seaType);

    /**
     * 用户：根据医生查询订单
     */
    List<AppointmentDto> getDoctor(@Param("doctorId") Integer doctorId);

    /**
     * 用户：查询自己的订单
     */
    List<AppointmentDto> getClient(@Param("clientId") Integer clientId);

    /**
     * 医生：查询自己的订单
     */
    List<AppointmentDto2> getDoctorLog(@Param("doctorId") Integer doctorId);
}
