package com.ship.security.login;

import com.ship.common.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

/**
 * 自定义登录成功的处理器
 */
public class LoginHandler implements AuthenticationSuccessHandler, AuthenticationFailureHandler {
    private static final Logger log = LoggerFactory.getLogger(LoginHandler.class);

    /**
     * 登录成功
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) {
        log.info("登录成功处理器调用1");
        log.info("登录的用户信息是：\n" + authentication.getPrincipal());
        req.getSession().setAttribute(
                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                SecurityContextHolder.getContext());
        // 直接返回Json
        JsonUtil.write(Result.success("登录成功"), resp);
    }

    /***
     * 登录失败
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException ae) {
        log.info("这是报错的信息，但一般不用：\n" + ae.getMessage());
        if (ae instanceof UsernameNotFoundException) {
            JsonUtil.write(Result.error("用户名未找到"), resp);
        } else if (ae instanceof BadCredentialsException) {
            JsonUtil.write(Result.error("密码错误"), resp);
        } else {
            JsonUtil.write(Result.error("登录失败"), resp);
        }
    }
}
