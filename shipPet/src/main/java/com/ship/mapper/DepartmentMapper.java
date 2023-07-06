package com.ship.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ship.util.PageUtil;
import com.ship.vo.NameVo;
import com.ship.entity.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 部门表 Mapper 接口
 * </p>
 *
 * @author MixJade
 * @since 2022-12-22
 */
@Mapper
public interface DepartmentMapper extends BaseMapper<Department> {
    int selectDepartmentCount(@Param("departmentName") String departmentName);

    List<Department> selectDepartmentPage(@Param("departmentName") String departmentName, @Param("pu") PageUtil pu);

    @Select("SELECT department_id as roleId, department_name as roleName FROM department WHERE is_del = '0'")
    List<NameVo> selectName();
}
