package com.forge.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.forge.vo.NameVo;
import com.forge.vo.Page;
import com.forge.entity.Department;

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
    boolean deleteById(long departmentId);

    /**
     * 分页查询
     *
     * @param departmentName 客户姓名，记得加模糊判别条件
     * @param numPage        当前页码
     * @param pageSize       当前一页的最大条数
     * @return 数据的总长度，与分页查询结果
     */
    Page<Department> selectByPage(String departmentName, int numPage, int pageSize);

    /**
     * 查询名字与对应id
     *
     * @return 相应角色名字与id
     */
    List<NameVo> selectName();
}
