package com.forge.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.forge.common.Result;
import com.forge.common.ResultEnum;
import com.forge.common.SendMail;
import com.forge.dto.RegisterDto;
import com.forge.entity.Client;
import com.forge.entity.Doctor;
import com.forge.entity.Employee;
import com.forge.mapper.ClientMapper;
import com.forge.shiro.authentic.RealmEnum;
import com.forge.shiro.authentic.MyToken;
import com.forge.vo.LoginVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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
     * 退出登录，清空数据
     */
    @GetMapping("/bye")
    public Result logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new Result(ResultEnum.LOGOUT);
    }
    /**
     * 没有角色权限
     */
    @GetMapping("/noPower")
    public Result noPower() {
        return new Result(ResultEnum.NO_POWER);
    }

    /**
     * 未登录的重定向
     *
     * @return 未登录
     */
    @GetMapping("/noLogin")
    public Result noLogin() {
        return new Result(ResultEnum.NO_LOGIN);
    }

    /**
     * 管理员登录
     *
     * @param loginVo 用户名与密码与记住我
     * @return 登录成功与失败
     */
    @PostMapping("/admin")
    public Result employeeLogin(@RequestBody LoginVo loginVo) {
        // 封装到token
        AuthenticationToken token = new MyToken(loginVo, RealmEnum.EMPLOYEE);
        // 进行验证
        return getResult(token);
    }

    /**
     * 用户登录
     *
     * @param loginVo 用户名与密码与记住我
     * @return 登录成功与失败
     */
    @PostMapping("/user")
    public Result clientLogin(@RequestBody LoginVo loginVo) {
        AuthenticationToken token = new MyToken(loginVo, RealmEnum.CLIENT);
        return getResult(token);
    }

    /**
     * 医生登录
     *
     * @param loginVo 用户名与密码与记住我
     * @return 登录成功与失败
     */
    @PostMapping("/doctor")
    public Result doctorLogin(@RequestBody LoginVo loginVo) {
        AuthenticationToken token = new MyToken(loginVo, RealmEnum.DOCTOR);
        return getResult(token);
    }

    /**
     * 管理员与用户登录的验证
     *
     * @param token 输入的账号、密码、记住我、验证角色
     * @return 验证结果
     */
    private Result getResult(AuthenticationToken token) {
        Subject subject = SecurityUtils.getSubject();// 获取subject
        try {
            subject.login(token);// 进行验证
            return Result.success("登录成功");
        } catch (IncorrectCredentialsException e) {
            return Result.error("登录密码错误");
        } catch (UnknownAccountException e) {
            return Result.error("登录账号不存在");
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return Result.error("登录失败");
        }
    }

    /**
     * 判断当前的角色进行跳转
     *
     * @return 当前登录的是管理员或用户或没有
     */
    @GetMapping
    public String getLogin() {
        Object principal = SecurityUtils.getSubject().getPrincipal();
        if (principal instanceof Employee) {
            return "admin";
        } else if (principal instanceof Client) {
            return "user";
        } else if (principal instanceof Doctor) {
            return "doctor";
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
        if (SecurityUtils.getSubject().getPrincipal() instanceof Client client)
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
        if (regUse == null || regUse.isBlank()) return Result.success("空白字符");
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
        if (username == null || username.isBlank()) return Result.error("用户名不能为空");
        String checkCode = registerDto.getCheckCode();
        String sessionCode = (String) session.getAttribute(registerDto.getClientTel());
        if (checkCode == null || checkCode.equals("")) return Result.error("验证码不能为空");
        else if (sessionCode == null) return Result.error("未发送验证码");
        else if (sessionCode.equals(checkCode.toUpperCase())) {
            session.invalidate();//销毁验证码
            // 设置部分默认信息
            registerDto.setClientAge(LocalDate.now());
            String password = registerDto.getClientPassword();
            registerDto.setClientPassword((new Md5Hash(password, "pet")).toHex());
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
        if (mail == null || mail.isBlank()) return Result.error("未获取到邮箱");
        String sessionCode = (String) session.getAttribute(mail);
        if (checkCode == null || checkCode.equals("")) return Result.error("验证码不能为空");
        else if (sessionCode == null) return Result.error("未发送验证码");
        else if (sessionCode.equals(checkCode.toUpperCase())) {
            session.invalidate();//销毁验证码
            // 重新设置密码
            UpdateWrapper<Client> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("client_username", registerDto.getClientUsername());
            String password = (new Md5Hash(registerDto.getClientPassword(), "pet")).toHex();
            updateWrapper.set("client_password", password);
            return Result.choice("密码重置", clientMapper.update(null, updateWrapper) > 0);
        } else return Result.error("验证码不正确");
    }
}
