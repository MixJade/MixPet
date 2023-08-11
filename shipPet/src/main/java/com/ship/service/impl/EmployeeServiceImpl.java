package com.ship.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ship.common.PhotoEnum;
import com.ship.mapper.EmployeeMapper;
import com.ship.model.entity.Employee;
import com.ship.service.IEmployeeService;
import com.ship.util.StrUtil;
import org.springframework.stereotype.Service;

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
    @Override
    public boolean deleteById(Integer employeeId) {
        return this.lambdaUpdate()
                .eq(Employee::getEmployeeId, employeeId)
                .set(Employee::getIsDel, System.currentTimeMillis())
                .update();
    }

    @Override
    public boolean deleteByIds(List<Integer> idGroup) {
        return this.lambdaUpdate()
                .in(Employee::getEmployeeId, idGroup)
                .set(Employee::getIsDel, System.currentTimeMillis())
                .update();
    }

    @Override
    public IPage<Employee> selectByPage(String employeeName, int numPage, int pageSize) {
        return baseMapper.selectEmployeePage(new Page<>(numPage, pageSize), employeeName);
    }

    @Override
    public boolean addEmployee(Employee em) {
        if (StrUtil.isWhite(em.getEmployeePhoto())) em.setEmployeePhoto(PhotoEnum.CLIENT.getPhotoName());
        if (StrUtil.isWhite(em.getEmployeePassword())) em.setEmployeePassword("123456");
        em.setEmployeePassword(StrUtil.tranPwd(em.getEmployeePassword()));
        return save(em);
    }
}
