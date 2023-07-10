package com.ship.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ship.entity.Employee;
import com.ship.mapper.EmployeeMapper;
import com.ship.service.IEmployeeService;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 * 员工表 服务实现类
 * </p>
 *
 * @author MixJade
 * @since 2022-12-22
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {
    @Override
    public boolean deleteById(long employeeId) {
        String delDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        return this.lambdaUpdate()
                .eq(Employee::getEmployeeId, employeeId)
                .set(Employee::getIsDel, delDate)
                .update();
    }

    @Override
    public IPage<Employee> selectByPage(String employeeName, int numPage, int pageSize) {
        return baseMapper.selectEmployeePage(new Page<>(numPage, pageSize), employeeName);
    }
}
