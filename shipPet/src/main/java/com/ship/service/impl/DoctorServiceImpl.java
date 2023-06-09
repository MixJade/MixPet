package com.ship.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ship.dto.DoctorDto;
import com.ship.entity.Doctor;
import com.ship.mapper.DoctorMapper;
import com.ship.service.IDoctorService;
import com.ship.util.CodeEnum;
import com.ship.vo.NameVo;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 医生表，外键部门 服务实现类
 * </p>
 *
 * @author MixJade
 * @since 2023-01-02
 */
@Service
public class DoctorServiceImpl extends ServiceImpl<DoctorMapper, Doctor> implements IDoctorService {
    @Override
    public boolean deleteById(long doctorId) {
        String delDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        return this.lambdaUpdate()
                .eq(Doctor::getDoctorId, doctorId)
                .set(Doctor::getIsDel, delDate)
                .update();
    }

    @Override
    public boolean deleteByIds(List<Long> idGroup) {
        String delDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        return this.lambdaUpdate()
                .in(Doctor::getDoctorId, idGroup)
                .set(Doctor::getIsDel, delDate)
                .update();
    }

    @Override
    public IPage<DoctorDto> selectByPage(String doctorName, String departmentName, int numPage, int pageSize) {
        return baseMapper.selectDoctorPage(new Page<>(numPage, pageSize), doctorName, departmentName);
    }

    @Override
    public DoctorDto selectById(long doctorId) {
        return baseMapper.selectOneId(doctorId);
    }

    @Override
    public List<NameVo> selectName() {
        return baseMapper.selectName();
    }

    @Override
    public List<NameVo> selectByDepartment(long departmentId) {
        return baseMapper.selectByDepartment(departmentId);
    }

    @Override
    public boolean save(Doctor doctor) {
        doctor.setDoctorCode(CodeEnum.DOCTOR.newCode(baseMapper.getMaxId()));
        return super.save(doctor);
    }
}
