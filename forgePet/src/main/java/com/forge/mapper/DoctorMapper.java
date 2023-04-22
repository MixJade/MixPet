package com.forge.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.forge.common.PageUntil;
import com.forge.dto.DoctorDto;
import com.forge.vo.NameVo;
import com.forge.entity.Doctor;
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
    boolean deleteId(@Param("delDate") String delDate, @Param("doctorId") long doctorId);

    boolean deleteIdGroup(@Param("delDate") String delDate, @Param("idGroup") long[] idGroup);

    int selectDoctorCount(@Param("doctorName") String doctorName, @Param("departmentName") String departmentName);

    List<DoctorDto> selectDoctorPage(@Param("doctorName") String doctorName, @Param("departmentName") String departmentName,@Param("pu") PageUntil pu);

    DoctorDto selectOneId(@Param("doctorId") long doctorId);

    @Select("SELECT doctor_id as roleId,doctor_name as roleName FROM doctor WHERE is_del = '0'")
    List<NameVo> selectName();

    @Select("SELECT doctor_id as roleId, doctor_name as roleName FROM doctor WHERE is_del = '0' AND department_id=#{departmentId}")
    List<NameVo> selectByDepartment(long departmentId);

    @Select("select max(doctor_id) from doctor")
    Long getMaxId();
}
