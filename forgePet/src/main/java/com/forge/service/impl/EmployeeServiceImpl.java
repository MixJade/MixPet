package com.forge.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.forge.vo.Page;
import com.forge.entity.Employee;
import com.forge.mapper.EmployeeMapper;
import com.forge.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
    public Page<List<Employee>> selectByPage(String employeeName, int numPage, int pageSize) {
        int maxCount = employeeMapper.selectEmployeeCount(employeeName);
        int needBegin = (numPage - 1) * pageSize;
        if (needBegin >= maxCount) needBegin = (maxCount / pageSize - 1) * pageSize;
        var employeeList = employeeMapper.selectEmployeePage(employeeName, needBegin, pageSize);
        return new Page<>(employeeList, maxCount);
    }
}
