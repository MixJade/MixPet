package com.forge.shiro.realm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.forge.entity.Doctor;
import com.forge.mapper.DoctorMapper;
import com.forge.shiro.RoleConst;
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
 * 医生登录验证
 */
@Component
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
public class DoctorRealm extends AuthorizingRealm {
    private static final Logger log = LoggerFactory.getLogger(DoctorRealm.class);
    private final DoctorMapper doctorMapper;

    @Lazy
    @Autowired
    public DoctorRealm(DoctorMapper doctorMapper) {
        this.doctorMapper = doctorMapper;
    }

    /**
     * 当前登录医生授权
     *
     * @param principalCollection 身份集合
     * @return 医生角色
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        if (!(principalCollection.getPrimaryPrincipal() instanceof Doctor)) return null;
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRole(RoleConst.DOCTOR);
        log.info("当前角色为医生");
        return info;
    }

    /**
     * 登录认证
     *
     * @param authenticationToken 登录时提交的凭据信息
     * @return AuthenticationInfo对象
     * @throws AuthenticationException 登录凭证失效
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        if (authenticationToken == null) {
            log.warn("啥也不是");
        } else {
            // 获取医生身份
            String name = authenticationToken.getPrincipal().toString();
            // 从数据库获取医生信息
            QueryWrapper<Doctor> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("doctor_code", name);
            Doctor doctor = doctorMapper.selectOne(queryWrapper);
            // 判断非空，将数据封装
            if (doctor != null) {
                return new SimpleAuthenticationInfo(doctor, doctor.getDoctorPassword(), ByteSource.Util.bytes("pet"), name);
            }
        }
        return null;
    }
}
