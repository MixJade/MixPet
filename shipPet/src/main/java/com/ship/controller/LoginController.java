package com.ship.controller;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.ship.common.Result;
import com.ship.common.SendMail;
import com.ship.model.dto.RegisterDto;
import com.ship.model.entity.Client;
import com.ship.model.entity.Doctor;
import com.ship.model.entity.Employee;
import com.ship.mapper.ClientMapper;
import com.ship.security.model.RoleEnum;
import com.ship.util.StrUtil;
import com.ship.util.UserUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

/**
 * 登录与注册模块
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    private final ClientMapper clientMapper;
    private final SendMail sendMail;

    @Autowired
    public LoginController(ClientMapper clientMapper, SendMail sendMail) {
        this.clientMapper = clientMapper;
        this.sendMail = sendMail;
    }

    /**
     * 判断当前的角色进行跳转
     *
     * @return 当前登录的是管理员或用户或没有
     */
    @GetMapping
    public RoleEnum getLogin() {
        Object principal = UserUtil.getUser();
        if (principal instanceof Employee) {
            return RoleEnum.EMPLOYEE;
        } else if (principal instanceof Client) {
            return RoleEnum.CLIENT;
        } else if (principal instanceof Doctor) {
            return RoleEnum.DOCTOR;
        }
        return null;
    }


    /**
     * 登录成功的用户信息，可能在未登录的情况
     *
     * @return id，账号，名字，电话，照片
     */
    @GetMapping("/user")
    public Client getUser() {
        if (UserUtil.getUser() instanceof Client client)
            return clientMapper.selectById(client.getClientId());
        return null;
    }

    /**
     * 发送验证码邮件，并将之存入session
     *
     * @param mail    接受者邮箱 1114185977@qq.com
     * @param session 建立session所需
     */
    @GetMapping("/regMail")
    public void sendMail(String mail, HttpSession session) {
        String code = sendMail.sendQQEmail(mail);
        if (code != null) session.setAttribute(mail, code);
    }

    /**
     * 注册时检查当前账号是否存在
     *
     * @param regUse 用户名
     * @return 不存在
     */
    @GetMapping("/isExist")
    public Result isExist(String regUse) {
        if (StrUtil.isWhite(regUse)) return Result.success("空白字符");
        int nowExist = clientMapper.isExist(regUse);
        if (nowExist == 0) return Result.success("用户不存在");
        else return Result.error("该用户已存在");
    }

    /**
     * 进行注册
     *
     * @param registerDto 客户信息，附带验证码
     * @param session     获取服务端验证码所需
     * @return 注册成功
     */
    @PostMapping("/register")
    public Result register(@RequestBody RegisterDto registerDto, HttpSession session) {
        String username = registerDto.getClientUsername();
        if (StrUtil.isWhite(username)) return Result.error("用户名不能为空");
        String checkCode = registerDto.getCheckCode();
        String sessionCode = (String) session.getAttribute(registerDto.getClientTel());
        if (StrUtil.isWhite(checkCode)) return Result.error("验证码不能为空");
        else if (sessionCode == null) return Result.error("未发送验证码");
        else if (sessionCode.equals(checkCode.toUpperCase())) {
            session.invalidate();//销毁验证码
            // 设置部分默认信息
            registerDto.setClientAge(LocalDate.now());
            String password = registerDto.getClientPassword();
            registerDto.setClientPassword(StrUtil.tranPwd(password));
            return Result.choice("注册", clientMapper.insert(registerDto) > 0);
        } else return Result.error("验证码不正确");
    }

    /**
     * 找回密码：验证账号与用户名，发送验证码
     *
     * @param mail     账号所对应邮箱
     * @param username 账号
     * @param session  建立session所需
     * @return 邮箱不匹配、验证成功
     */
    @GetMapping("/finMail")
    public Result sendMail(String mail, String username, HttpSession session) {
        if (StrUtil.isWhite(username)) return Result.error("账号不能为空");
        if (StrUtil.isWhite(mail)) return Result.error("邮箱不能为空");
        if (clientMapper.sureMail(username, mail) == 0) return Result.error("账号邮箱不匹配");
        String code = sendMail.sendQQEmail(mail);
        if (code != null) session.setAttribute(mail, code);
        return Result.success("发送成功");
    }

    /**
     * 找回密码
     *
     * @param registerDto 客户信息，附带验证码
     * @param session     获取服务端验证码所需
     * @return 注册成功
     */
    @PostMapping("/find")
    public Result find(@RequestBody RegisterDto registerDto, HttpSession session) {
        String checkCode = registerDto.getCheckCode();
        String mail = registerDto.getClientTel();
        if (StrUtil.isWhite(mail)) return Result.error("未获取到邮箱");
        String sessionCode = (String) session.getAttribute(mail);
        if (StrUtil.isWhite(checkCode)) return Result.error("验证码不能为空");
        else if (sessionCode == null) return Result.error("未发送验证码");
        else if (sessionCode.equals(checkCode.toUpperCase())) {
            session.invalidate();//销毁验证码
            // 重新设置密码
            var updateWrapper = new LambdaUpdateWrapper<Client>();
            String username = registerDto.getClientUsername();
            updateWrapper.eq(StringUtils.isNotBlank(username), Client::getClientUsername, username);
            String password = StrUtil.tranPwd(registerDto.getClientPassword());
            updateWrapper.set(Client::getClientPassword, password);
            return Result.choice("密码重置", clientMapper.update(null, updateWrapper) > 0);
        } else return Result.error("验证码不正确");
    }
}
