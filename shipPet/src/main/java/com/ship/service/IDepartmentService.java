package com.ship.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ship.common.Result;
import com.ship.model.dto.DepartmentDto;
import com.ship.model.entity.Department;
import com.ship.model.dto.NameDo;

import java.util.List;

/**
 * <p>
 * 部门表 服务类
 * </p>
 *
 * @author MixJade
 * @since 2022-12-22
 */
public interface IDepartmentService extends IService<Department> {
    /**
     * 单个逻辑删除
     *
     * @param departmentId 客户id
     * @return 是否删除成功
     */
    Result deleteById(Integer departmentId);

    /**
     * 批量逻辑删除
     *
     * @param idGroup 一组id
     * @return 是否批量删除成功
     */
    Result deleteByIds(List<Integer> idGroup);

    /**
     * 分页查询
     *
     * @param departmentName 客户姓名，记得加模糊判别条件
     * @param numPage        当前页码
     * @param pageSize       当前一页的最大条数
     * @return 数据的总长度，与分页查询结果
     */
    IPage<DepartmentDto> selectByPage(String departmentName, int numPage, int pageSize);

    /**
     * 查询名字与对应id
     *
     * @return 相应角色名字与id
     */
    List<NameDo> selectName();

    /**
     * 【内联查询】名下有医生的科室，带上当前科室
     *
     * @param departmentId 当前科室ID
     * @return 科室名称+ID
     */
    List<NameDo> selectNameHaveDoctor(Integer departmentId);

    /**
     * 更新科室，让主任可以为空
     *
     * @param department 科室信息
     * @return 更新成功
     */
    boolean updateDepart(Department department);
}
