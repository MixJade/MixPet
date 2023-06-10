package com.ship.security.login;

import com.ship.security.details.ClientDetails;
import com.ship.security.details.DoctorDetails;
import com.ship.security.details.EmployeeDetails;
import com.ship.security.model.MyUser;
import com.ship.security.model.RoleEnum;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.security.web.authentication.rememberme.InvalidCookieException;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * 记住我
 */
@Component
public class RememberMeService extends AbstractRememberMeServices {
    private final Log log = LogFactory.getLog(this.getClass());
    private final ClientDetails clientDetails;
    private final DoctorDetails doctorDetails;
    private final EmployeeDetails employeeDetails;

    public RememberMeService(ClientDetails clientDetails,
                             DoctorDetails doctorDetails,
                             EmployeeDetails employeeDetails) {
        super("MixJade", clientDetails);
        log.info("手动启动");
        this.clientDetails = clientDetails;
        this.doctorDetails = doctorDetails;
        this.employeeDetails = employeeDetails;
    }

    @Override
    protected void onLoginSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication sucAut) {
        log.info("登录成功的记住我处理");
        if (sucAut.getPrincipal() instanceof MyUser myUser) {
            String username = myUser.username();
            String password = myUser.password();
            RoleEnum roleEnum = myUser.roleEnum();
            if (!StringUtils.hasLength(username)) {
                log.info("用户名为空");
                return;
            }
            long expiryTime = System.currentTimeMillis();
            // 记住我3个小时
            int lifeHour = 3;
            expiryTime += 1000L * 3600L * (long) lifeHour;
            String signatureValue = this.makeTokenSignature(expiryTime, username, password, roleEnum.name());
            setCookie(new String[]{username, Long.toString(expiryTime), roleEnum.name(), "MD5", signatureValue}, lifeHour * 3600, req, resp);
        }
    }

    @Override
    protected boolean rememberMeRequested(HttpServletRequest req, String parameter) {
        log.info("自定义RememberMe处理;");
        if (req.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
            log.info("芝士JSON");
            boolean rememberMe = (boolean) req.getAttribute("rememberMe");
            log.info("记住我处理器收到的参数是:" + rememberMe);
            return rememberMe;
        } else return false;
    }

    @Override
    protected UserDetails processAutoLoginCookie(String[] cookieTokens, HttpServletRequest req, HttpServletResponse resp) throws RememberMeAuthenticationException, UsernameNotFoundException {
        if (cookieTokens.length != 4 && cookieTokens.length != 5) {
            throw new InvalidCookieException("这Cookie不对啊");
        }
        log.info("收到的cookie" + Arrays.toString(cookieTokens));
        String name = cookieTokens[0];
        // TODO 事实上还要判断当前cookie是否过期
        if (cookieTokens[2].equals(RoleEnum.EMPLOYEE.name())){
            return employeeDetails.loadUserByUsername(name);
        } else if (cookieTokens[2].equals(RoleEnum.DOCTOR.name())) {
            return doctorDetails.loadUserByUsername(name);
        } else return clientDetails.loadUserByUsername(name);
    }

    /***
     * 设置token内容
     */
    private String makeTokenSignature(long expiryTime, String username, String password, String role) {
        String data = username + ":" + expiryTime + ":" + password + ":" + role + ":" + this.getKey();
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            return new String(Hex.encode(digest.digest(data.getBytes())));
        } catch (NoSuchAlgorithmException var8) {
            throw new IllegalStateException("Token加密失败");
        }
    }
}
