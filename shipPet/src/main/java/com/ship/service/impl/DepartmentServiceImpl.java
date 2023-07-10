package com.ship.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ship.vo.NameVo;
import com.ship.entity.Department;
import com.ship.mapper.DepartmentMapper;
import com.ship.service.IDepartmentService;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
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
        String delDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        return this.lambdaUpdate()
                .eq(Department::getDepartmentId, departmentId)
                .set(Department::getIsDel, delDate)
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
