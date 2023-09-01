package com.ship.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ship.common.Result;
import com.ship.mapper.DepartmentMapper;
import com.ship.model.dto.DepartmentDto;
import com.ship.model.entity.Department;
import com.ship.model.entity.Doctor;
import com.ship.model.vo.NameVo;
import com.ship.service.IDepartmentService;
import com.ship.service.IDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final IDoctorService doctorService;

    @Autowired
    public DepartmentServiceImpl(IDoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @Override
    public Result deleteById(Integer departmentId) {
        if (doctorService.lambdaQuery().eq(Doctor::getDepartmentId, departmentId).count() > 0)
            return Result.error("当前科室下有人");
        return Result.choice("删除单个", this.lambdaUpdate()
                .eq(Department::getDepartmentId, departmentId)
                .set(Department::getIsDel, System.currentTimeMillis())
                .update());
    }

    @Override
    public Result deleteByIds(List<Integer> idGroup) {
        if (idGroup.size() < 1) return Result.error("请选择数据"); // IN语句中没有数据会报错
        if (doctorService.lambdaQuery().in(Doctor::getDepartmentId, idGroup).count() > 0) {
            if (idGroup.size() == 1) return Result.error("所选科室有人");
            else return Result.error("部分所选科室有人");
        }
        return Result.choice("删除多个", lambdaUpdate()
                .in(Department::getDepartmentId, idGroup)
                .set(Department::getIsDel, System.currentTimeMillis())
                .update());
    }

    @Override
    public IPage<DepartmentDto> selectByPage(String departmentName, int numPage, int pageSize) {
        return baseMapper.selectDepartmentPage(new Page<>(numPage, pageSize), departmentName);
    }

    @Override
    public List<NameVo> selectName() {
        return baseMapper.selectName();
    }

    @Override
    public List<NameVo> selectNameHaveDoctor(Integer departmentId) {
        return baseMapper.selectNameHaveDoctor(departmentId);
    }

    @Override
    public boolean updateDepart(Department department) {
        return baseMapper.updateDepart(department);
    }
}
