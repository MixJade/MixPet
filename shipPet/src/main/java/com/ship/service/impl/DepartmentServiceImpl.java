package com.ship.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ship.util.PageUtil;
import com.ship.vo.NameVo;
import com.ship.vo.Page;
import com.ship.entity.Department;
import com.ship.mapper.DepartmentMapper;
import com.ship.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final DepartmentMapper departmentMapper;

    @Autowired
    public DepartmentServiceImpl(DepartmentMapper departmentMapper) {
        this.departmentMapper = departmentMapper;
    }

    @Override
    public boolean deleteById(long departmentId) {
        String delDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        return departmentMapper.deleteId(delDate, departmentId);
    }

    @Override
    public Page<Department> selectByPage(String departmentName, int numPage, int pageSize) {
        int maxCount = departmentMapper.selectDepartmentCount(departmentName);
        PageUtil pu = PageUtil.pu(numPage, pageSize, maxCount);
        var departmentList = departmentMapper.selectDepartmentPage(departmentName, pu);
        return new Page<>(departmentList, maxCount);
    }

    @Override
    public List<NameVo> selectName() {
        return departmentMapper.selectName();
    }
}
