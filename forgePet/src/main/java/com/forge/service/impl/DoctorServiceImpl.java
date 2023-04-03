package com.forge.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.forge.common.CodeEnum;
import com.forge.dto.DoctorDto;
import com.forge.vo.Page;
import com.forge.entity.Doctor;
import com.forge.mapper.DoctorMapper;
import com.forge.service.IDoctorService;
import com.forge.vo.NameVo;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final DoctorMapper doctorMapper;

    @Autowired
    public DoctorServiceImpl(DoctorMapper doctorMapper) {
        this.doctorMapper = doctorMapper;
    }

    @Override
    public boolean deleteById(long doctorId) {
        String delDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        return doctorMapper.deleteId(delDate, doctorId);
    }

    @Override
    public boolean deleteByIds(long[] idGroup) {
        String delDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        return doctorMapper.deleteIdGroup(delDate, idGroup);
    }

    @Override
    public Page<List<DoctorDto>> selectByPage(String doctorName, String departmentName, int numPage, int pageSize) {
        int maxCount = doctorMapper.selectDoctorCount(doctorName, departmentName);
        int needBegin = (numPage - 1) * pageSize;
        if (needBegin >= maxCount) needBegin = (maxCount / pageSize - 1) * pageSize;
        var doctorList = doctorMapper.selectDoctorPage(doctorName, departmentName, needBegin, pageSize);
        return new Page<>(doctorList, maxCount);
    }

    @Override
    public DoctorDto selectById(long doctorId) {
        return doctorMapper.selectOneId(doctorId);
    }

    @Override
    public List<NameVo> selectName() {
        return doctorMapper.selectName();
    }

    @Override
    public List<NameVo> selectByDepartment(long departmentId) {
        return doctorMapper.selectByDepartment(departmentId);
    }

    @Override
    public boolean save(Doctor doctor) {
        doctor.setDoctorCode(CodeEnum.DOCTOR.newCode(doctorMapper.getMaxId()));
        return super.save(doctor);
    }
}
