package com.ship.security.details;

import com.ship.model.entity.Doctor;
import com.ship.security.model.RoleConst;
import com.ship.security.model.MyUser;
import com.ship.security.model.RoleEnum;
import com.ship.service.IDoctorService;
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
 * 医生登录时
 */
@Component
public class DoctorDetails implements UserDetailsService {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final IDoctorService doctorService;

    @Autowired
    public DoctorDetails(IDoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("从数据库根据医生名查询数据");
        // 从数据库获取用户信息
        Doctor doctor = doctorService.lambdaQuery().eq(Doctor::getUsername, username).one();
        if (doctor == null) {
            log.warn("没有这个用户");
            throw new UsernameNotFoundException("用户名不存在");
        }
        // 校验所需要的密码
        String password = doctor.getDoctorPassword();
        // 授予角色
        // 授予角色
        int level = doctor.getAuthLv();
        List<String> roleList = new ArrayList<>();
        switch (level) {
            case 8:
                roleList.add(RoleConst.ADMIN);
            case 6:
                roleList.add(RoleConst.MANAGER);
            case 4:
                roleList.add(RoleConst.DOCTOR);
            case 2:
                roleList.add(RoleConst.NURSE);
            default:
                roleList.add(RoleConst.LOSER);
        }
        var authorities = AuthorityUtils.createAuthorityList(roleList);
        return new MyUser(username, password, authorities, RoleEnum.DOCTOR, doctor);
    }
}
