package com.ship.security.login;

import com.ship.common.ResultConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

public class PowerHandler implements LogoutSuccessHandler, AccessDeniedHandler, AuthenticationEntryPoint {
    /**
     * 退出登陆成功
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication auth) {
        JsonUtil.write(ResultConst.LOGOUT, resp);
    }

    /**
     * 没有权限，这个是装饰，真正的在异常切片里
     */
    @Override
    public void handle(HttpServletRequest req, HttpServletResponse resp, AccessDeniedException ade) {
        JsonUtil.write(ResultConst.NO_POWER, resp);
    }

    /**
     * 尚未登陆
     */
    @Override
    public void commence(HttpServletRequest req, HttpServletResponse resp, AuthenticationException ae) {
        JsonUtil.write(ResultConst.NO_LOGIN, resp);
    }
}
