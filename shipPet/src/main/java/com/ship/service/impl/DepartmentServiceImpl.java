package com.ship.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ship.entity.Department;
import com.ship.mapper.DepartmentMapper;
import com.ship.service.IDepartmentService;
import com.ship.vo.NameVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author MixJade
 * @since 2022-12-22
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {
    @Override
    public boolean deleteById(long departmentId) {
        return this.lambdaUpdate()
                .eq(Department::getDepartmentId, departmentId)
                .set(Department::getIsDel, String.valueOf(System.currentTimeMillis()))
                .update();
    }

    @Override
    public IPage<Department> selectByPage(String departmentName, int numPage, int pageSize) {
        return baseMapper.selectDepartmentPage(new Page<>(numPage, pageSize), departmentName);
    }

    @Override
    public List<NameVo> selectName() {
        return baseMapper.selectName();
    }
}
