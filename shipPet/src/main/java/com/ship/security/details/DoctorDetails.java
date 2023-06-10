package com.ship.security.details;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ship.entity.Doctor;
import com.ship.mapper.DoctorMapper;
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

/**
 * 医生登录时
 */
@Component
public class DoctorDetails implements UserDetailsService {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final DoctorMapper doctorMapper;

    @Autowired
    public DoctorDetails(DoctorMapper doctorMapper) {
        this.doctorMapper = doctorMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("从数据库根据医生名查询数据");
        // 从数据库获取用户信息
        QueryWrapper<Doctor> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("doctor_code", username);
        Doctor doctor = doctorMapper.selectOne(queryWrapper);
        if (doctor == null) {
            log.warn("没有这个用户");
            throw new UsernameNotFoundException("用户名不存在");
        }
        // 校验所需要的密码
        String password = doctor.getDoctorPassword();
        // 授予角色
        var authorities = AuthorityUtils.createAuthorityList(RoleConst.DOCTOR);
        return new MyUser(username, password, authorities, RoleEnum.DOCTOR);
    }
}
