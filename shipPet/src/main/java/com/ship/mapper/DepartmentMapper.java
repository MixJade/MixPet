package com.ship.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ship.model.dto.DepartmentDto;
import com.ship.model.entity.Department;
import com.ship.model.vo.NameVo;
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
    IPage<DepartmentDto> selectDepartmentPage(IPage<Department> page, @Param("departmentName") String departmentName);

    @Select("SELECT department_id as roleId, department_name as roleName FROM department")
    List<NameVo> selectName();
    /**
     * 更新科室，让主任可以为空
     *
     * @param department 科室信息
     * @return 更新成功
     */
    boolean updateDepart(Department department);
}
