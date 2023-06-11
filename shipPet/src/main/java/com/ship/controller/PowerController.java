package com.ship.controller;

import com.ship.common.Result;
import com.ship.common.ResultConst;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/power")
public class PowerController implements ErrorController {
    /**
     * 自定义错误的请求，这里路径在配置文件中配置
     *
     * @param req SpringBoot转发的请求
     * @return 401、404、403、500
     */
    @RequestMapping("/error")
    public Result handleError(HttpServletRequest req) {
        Integer statusCode = (Integer) req.getAttribute("jakarta.servlet.error.status_code");
        return Result.error("请求失败：" + statusCode);
    }

    /**
     * 退出登录，清空数据
     */
    @GetMapping("/bye")
    public Result logout() {
        return ResultConst.LOGOUT;
    }

    /**
     * 没有角色权限
     * 这个接口理论上不会执行，因为自定义异常捕获
     */
    @GetMapping("/noPower")
    public Result noPower() {
        return ResultConst.NO_POWER;
    }

    /**
     * 未登录的重定向
     *
     * @return 未登录
     */
    @GetMapping("/noLogin")
    public Result noLogin() {
        return ResultConst.NO_LOGIN;
    }
}
