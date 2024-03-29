package com.forge.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.forge.vo.Page;
import com.forge.entity.Employee;

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
    boolean deleteById(long employeeId);

    /**
     * 分页查询
     *
     * @param employeeName 客户姓名，记得加模糊判别条件
     * @param numPage        当前页码
     * @param pageSize       当前一页的最大条数
     * @return 数据的总长度，与分页查询结果
     */
    Page<Employee> selectByPage(String employeeName, int numPage, int pageSize);
}
