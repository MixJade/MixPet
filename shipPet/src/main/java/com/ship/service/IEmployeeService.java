package com.ship.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ship.model.entity.Employee;

import java.util.List;

/**
 * <p>
 * 员工表 服务类
 * </p>
 *
 * @author MixJade
 * @since 2022-12-22
 */
public interface IEmployeeService extends IService<Employee> {
    /**
     * 单个逻辑删除
     *
     * @param employeeId 客户id
     * @return 是否删除成功
     */
    boolean deleteById(Integer employeeId);

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
     * @param employeeName 客户姓名，记得加模糊判别条件
     * @param numPage      当前页码
     * @param pageSize     当前一页的最大条数
     * @return 数据的总长度，与分页查询结果
     */
    IPage<Employee> selectByPage(String employeeName, int numPage, int pageSize);

}
