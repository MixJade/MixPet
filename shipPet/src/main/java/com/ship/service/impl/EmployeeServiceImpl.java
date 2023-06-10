package com.ship.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ship.util.PageUtil;
import com.ship.vo.Page;
import com.ship.entity.Employee;
import com.ship.mapper.EmployeeMapper;
import com.ship.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    @Override
    public boolean deleteById(long employeeId) {
        String delDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        return employeeMapper.deleteId(delDate, employeeId);
    }

    @Override
    public Page<Employee> selectByPage(String employeeName, int numPage, int pageSize) {
        int maxCount = employeeMapper.selectEmployeeCount(employeeName);
        PageUtil pu = PageUtil.pu(numPage, pageSize, maxCount);
        var employeeList = employeeMapper.selectEmployeePage(employeeName, pu);
        return new Page<>(employeeList, maxCount);
    }
}
