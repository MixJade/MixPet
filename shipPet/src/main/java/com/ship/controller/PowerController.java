package com.ship.controller;

import com.ship.common.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * SpringSecurity的控制器
 */
@RestController
@RequestMapping("/power")
@CrossOrigin // 没有意义的允许跨域，因为前端已经配置了
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
     * 将明文生成为密文，用作测试
     * <p>示例请求：/power/getPwd?password=123</p>
     *
     * @param password 明文密码
     */
    @GetMapping("/getPwd")
    public void getPwd(String password) {
        System.out.println("明文密码是：" + password + "\n"
                + "密码加密后：" + new BCryptPasswordEncoder().encode(password));
    }
}
