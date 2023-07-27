package com.ship.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ship.dto.AppointmentDto;
import com.ship.dto.AppointmentDto2;
import com.ship.entity.Appointment;

import java.util.List;

/**
 * <p>
 * 挂号单表 服务类
 * </p>
 *
 * @author MixJade
 * @since 2022-12-22
 */
public interface IAppointmentService extends IService<Appointment> {
    /**
     * 单个逻辑删除
     *
     * @param appointmentId 挂号单id
     * @return 是否删除成功
     */
    boolean deleteById(Integer appointmentId);

    /**
     * 批量逻辑删除
     *
     * @param idGroup 一组id
     * @return 是否批量删除成功
     */
    boolean deleteByIds(List<Integer> idGroup);

    /**
     * 分页查询
     *
     * @param seaName  模糊搜索所需姓名
     * @param seaType  姓名的种类
     * @param numPage  当前页码
     * @param pageSize 当前一页的最大条数
     * @return 数据的总长度，与分页查询结果
     */
    IPage<AppointmentDto> selectByPage(String seaName, int seaType, int numPage, int pageSize);

    /**
     * 根据医生查询挂号信息
     *
     * @param doctorId 医生id
     * @return 挂号信息
     */
    List<AppointmentDto> getDoctor(Integer doctorId);

    /**
     * 根据客户查询挂号信息
     *
     * @param clientId 客户id
     * @return 挂号信息
     */
    List<AppointmentDto> getClient(Integer clientId);

    /**
     * 医生：查询自己的订单
     */
    List<AppointmentDto2> getDoctorLog(Integer doctorId);
}
