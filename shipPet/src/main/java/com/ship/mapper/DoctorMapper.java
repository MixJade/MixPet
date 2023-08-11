package com.ship.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ship.model.dto.DoctorDto;
import com.ship.model.entity.Doctor;
import com.ship.model.dto.NameDo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 医生表，外键部门 Mapper 接口
 * </p>
 *
 * @author MixJade
 * @since 2023-01-02
 */
@Mapper
public interface DoctorMapper extends BaseMapper<Doctor> {
    IPage<DoctorDto> selectDoctorPage(IPage<DoctorDto> page, String doctorName, String departmentName);

    DoctorDto selectOneById(@Param("doctorId") Integer doctorId);

    @Select("SELECT doctor_id as roleId,doctor_name as roleName FROM doctor WHERE is_del=0")
    List<NameDo> selectName();

    @Select("SELECT doctor_id as roleId, doctor_name as roleName FROM doctor WHERE is_del = '0' AND department_id=#{departmentId}")
    List<NameDo> selectNameByDepartment(Integer departmentId);

    List<Doctor> selectByDepartmentId(Integer departmentId);

    boolean updateSelf(Doctor doctor);
}
