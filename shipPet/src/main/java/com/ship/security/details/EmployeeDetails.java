package com.ship.security.details;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ship.entity.Employee;
import com.ship.mapper.EmployeeMapper;
import com.ship.security.RoleConst;
import com.ship.security.model.MyUser;
import com.ship.security.model.RoleEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 管理员登录时
 */
@Component
public class EmployeeDetails implements UserDetailsService {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final EmployeeMapper employeeMapper;


    @Autowired
    public EmployeeDetails(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("从数据库根据管理员查询数据");
        // 从数据库获取用户信息
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("employee_username", username);
        Employee employee = employeeMapper.selectOne(queryWrapper);
        if (employee == null) {
            log.warn("没有这个管理员");
            throw new UsernameNotFoundException("管理员账号不存在");
        }
        // 校验所需要的密码
        String password = employee.getEmployeePassword();
        // 授予角色
        int level = employee.getEmployeeLevel();
        List<String> roleList = new ArrayList<>();
        switch (level) {
            case 6:
                roleList.add(RoleConst.ADMIN);
            case 4:
                roleList.add(RoleConst.MANAGER);
            case 2:
                roleList.add(RoleConst.NURSE);
            default:
                roleList.add(RoleConst.LOSER);
        }
        var authorities = AuthorityUtils.createAuthorityList(roleList);
        return new MyUser(username, password, authorities, RoleEnum.EMPLOYEE, employee);
    }
}
