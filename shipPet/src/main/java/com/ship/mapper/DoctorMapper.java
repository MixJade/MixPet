package com.ship.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ship.util.PageUtil;
import com.ship.dto.DoctorDto;
import com.ship.vo.NameVo;
import com.ship.entity.Doctor;
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
    int selectDoctorCount(@Param("doctorName") String doctorName, @Param("departmentName") String departmentName);

    List<DoctorDto> selectDoctorPage(@Param("doctorName") String doctorName, @Param("departmentName") String departmentName,@Param("pu") PageUtil pu);

    DoctorDto selectOneId(@Param("doctorId") long doctorId);

    @Select("SELECT doctor_id as roleId,doctor_name as roleName FROM doctor WHERE is_del = '0'")
    List<NameVo> selectName();

    @Select("SELECT doctor_id as roleId, doctor_name as roleName FROM doctor WHERE is_del = '0' AND department_id=#{departmentId}")
    List<NameVo> selectByDepartment(long departmentId);

    @Select("select max(doctor_id) from doctor")
    Long getMaxId();
}
