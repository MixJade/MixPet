package com.forge.shiro.realm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.forge.entity.Employee;
import com.forge.mapper.EmployeeMapper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Role;
import org.springframework.stereotype.Component;

/**
 * 员工登录验证
 */
@Component
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
public class EmployeeRealm extends AuthorizingRealm {
    private static final Logger log = LoggerFactory.getLogger(EmployeeRealm.class);
    private final EmployeeMapper employeeMapper;


    @Lazy
    @Autowired
    public EmployeeRealm(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    /**
     * 当前登录用户授权
     *
     * @param principalCollection 当前登录的用户信息
     * @return 当前用户的角色权值
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        if (principalCollection.getPrimaryPrincipal() instanceof Employee employee) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            int level = employee.getEmployeeLevel();
            switch (level) {
                case 6:
                    info.addRole("admin");
                case 4:
                    info.addRole("deputy");
                case 2:
                    info.addRole("nurse");
                default:
                    break;
            }
            log.info("当前角色：" + info.getRoles());
            return info;
        } else return null;
    }

    /**
     * 登录认证
     *
     * @param authenticationToken 登录时提交的凭据信息
     * @return AuthenticationInfo对象，内有序列化的员工对象
     * @throws AuthenticationException 登录凭证失效
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        if (authenticationToken == null) {
            log.warn("啥也不是");
        } else {
            // 获取输入账号
            String name = authenticationToken.getPrincipal().toString();
            // 从数据库获取用户信息
            QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("employee_username", name);
            Employee employee = employeeMapper.selectOne(queryWrapper);
            // 判断非空，将数据封装
            if (employee != null) {
                return new SimpleAuthenticationInfo(employee, employee.getEmployeePassword(), ByteSource.Util.bytes("pet"), name);
            }
        }
        return null;
    }
}
