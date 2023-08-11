# P2-Shiro整合

> spring整合shiro

* 注意shiro大部分的多角色验证默认都是and
* (1月12日更新)为了防止麻烦，我会让高权限角色同时也拥有低于它权限的角色
* 然后角色验证按照“门槛”来验证，即“这个操作最低所需角色”
* 这将通过没有break的switch来实现（在realm中）
* (1月15日更新)让realm存储当前登录的对象（尚硅谷教程只教存储名字）,对应对象一定要实现序列化接口

## 一、引入依赖

```xml
<!--shiro安全框架-->
<dependency>
    <groupId>org.apache.shiro</groupId>
    <artifactId>shiro-spring-boot-starter</artifactId>
    <version>1.10.1</version>
</dependency>
```

## 二、配置登录路径

以及没有对应角色时的跳转路径

```
shiro:
  loginUrl: /login.html
  unauthorizedUrl: /index.html
```

## 建立realm

> realm是shiro的核心，提供了角色认证与角色授权

ClientRealm.java

```
/**
 * 用户登录验证
 */
@Component
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
public class ClientRealm extends AuthorizingRealm {
    private ClientMapper clientMapper;

    @Lazy
    @Autowired
    public void setClientMapper(ClientMapper clientMapper) {
        this.clientMapper = clientMapper;
    }

    /**
     * 当前登录用户授权
     *
     * @param principalCollection 身份集合
     * @return 用户角色
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRole("client");
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
            System.out.println("啥也不是");
        } else {
            // 获取用户身份
            String name = authenticationToken.getPrincipal().toString();
            // 从数据库获取用户信息
            QueryWrapper<Client> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("client_username", name);
            Client client = clientMapper.selectOne(queryWrapper);
            // 判断非空，将数据封装
            if (client != null) {
                return new SimpleAuthenticationInfo(client, client.getClientPassword(), ByteSource.Util.bytes("pet"), name);
            }
        }
        return null;
    }
}
```

EmployeeRealm.java

```
/**
 * 员工登录验证
 */
@Component
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
public class EmployeeRealm extends AuthorizingRealm {
    private static final Logger log = LoggerFactory.getLogger(EmployeeRealm.class);
    private EmployeeMapper employeeMapper;

    @Lazy
    @Autowired
    public void setEmployeeMapper(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    /**
     * 当前登录用户授权
     *
     * @param principalCollection 当前登录的用户信息
     * @return 当前用户的角色权值
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
 SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        try {
            Employee employee = (Employee) principalCollection.getPrimaryPrincipal();
            int level = employee.getEmployeeLevel();
            switch (level) {
                case 6:
                    info.addRole("admin");
                case 4:
                    info.addRole("deputy");
                case 2:
                    info.addRole("nurse");
                default:
                    info.addRole("loser");
            }
            log.info("角色信息：" + info.getRoles());
            return info;
        } catch (BindingException e) {
            log.warn("没有找到相关角色");
            return null;
        }catch (ClassCastException e){
            log.warn("管理员没有登录");
            return null;
        }
    }

    /**
     * 登录认证
     *
     * @param authenticationToken 登录时提交的凭据信息
     * @return AuthenticationInfo对象，内有序列化的员工对象
     * @throws AuthenticationException 登录凭证失效
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        if (authenticationToken == null) {
            log.warn("啥也不是");
        } else {
            // 获取输入账号
            String name = authenticationToken.getPrincipal().toString();
            // 从数据库获取用户信息
            QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("employee_username", name);
            Employee employee = employeeMapper.selectOne(queryWrapper);
            // 判断非空，将数据封装
            if (employee != null) {
                return new SimpleAuthenticationInfo(employee, employee.getEmployeePassword(), ByteSource.Util.bytes("pet"), name);
            }
        }
        return null;
    }
}
```

## 建立realm选择器

> 默认的多realm验证会出现串台的情况，需要自己重写相关方法

枚举类RealmName.java

```
package com.forge.shiro.authentic;

public enum RealmName {
ClientRealm, EmployeeRealm
}

```

MyToken.java重写UsernamePasswordToken

```
public class MyToken extends UsernamePasswordToken {
    private final RealmName realmName;

    public MyToken(LoginDto loginVo, RealmName realmName) {
        super(loginVo.getUsername(), loginVo.getPassword(), loginVo.isRemember());
        this.realmName = realmName;
    }

    public RealmName getLoginType() {
        return realmName;
    }

}
```

MyRealmCheck.java根据类名包含来选择realm

```
public class MyRealmCheck extends ModularRealmAuthenticator {
    /**
     * 自定义的Realm验证，注意只能选出一个realm
     *
     * @param aToken 原来类型的token
     * @return 单个realm处理
     * @throws AuthenticationException 登录失败
     */
    @Override
    protected AuthenticationInfo doAuthenticate(AuthenticationToken aToken) throws AuthenticationException {
        assertRealmsConfigured();// 判断getRealms()是否为空
        MyToken myToken = (MyToken) aToken;// 强转自己的Token
        RealmName realmName = myToken.getLoginType();// 登录类型
        Collection<Realm> realms = getRealms();// 所有Realm
        // 使用类名包含枚举,区分realm
        for (Realm realm : realms) {
            if (realm.getName().contains(realmName.toString())) {
                return doSingleRealmAuthentication(realm, myToken);
            }
        }
        return super.doAuthenticate(aToken);
    }
}

```

## 写shiro配置类

> * shiro需要写配置类
> * 值得注意的是,需要在一些bean下面加上`@Role(BeanDefinition.ROLE_INFRASTRUCTURE)`
> * 意思是这个bean完全后台运行，不需要装配，不然会提示bean在装配之前引用

ShiroConfig.java
> 注意：拦截器配置亦有顺序

```
@Configuration
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
public class ShiroConfig {
    private EmployeeRealm employeeRealm;
    private ClientRealm clientRealm;

    @Autowired
    public void setEmployeeRealm(EmployeeRealm employeeRealm) {
        this.employeeRealm = employeeRealm;
    }

    @Autowired
    public void setClientRealm(ClientRealm clientRealm) {
        this.clientRealm = clientRealm;
    }

    /**
     * 配置securityManager
     *
     * @return DefaultWebSecurityManager对象
     */
    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public DefaultWebSecurityManager getDefaultWebSecurityManage() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //加密
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");
        // 加密对象存储在realm中，使用自己定义的MyRealmCheck
        securityManager.setAuthenticator(new MyRealmCheck());
        employeeRealm.setCredentialsMatcher(matcher);
        clientRealm.setCredentialsMatcher(matcher);
        List<Realm> realms = new ArrayList<>();
        realms.add(employeeRealm);
        realms.add(clientRealm);
        securityManager.setRealms(realms);
        // 记住我
        securityManager.setRememberMeManager(rememberMe());
        // 去掉JSESSIONID,防止重定向失败
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    /**
     * Shiro的过滤器设置
     *
     * @return 过滤器
     */
    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public DefaultShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition definition = new DefaultShiroFilterChainDefinition();
        // 不认证可以访问的路径
        definition.addPathDefinition("/login/**", "anon");
        definition.addPathDefinition("/assets/**", "anon");
        definition.addPathDefinition("/picture/**", "anon");
        definition.addPathDefinition("/login.html", "anon");
        definition.addPathDefinition("/noPower.html", "anon");
        definition.addPathDefinition("/index.html", "anon");
        definition.addPathDefinition("/favicon.ico", "anon");
        // 退出登录
        definition.addPathDefinition("/logout", "logout");
        // 限定角色访问
        definition.addPathDefinition("/backstage/**", "roles[nurse]");
        // 需要进行登录认证的路径,一定要放在下面，不然前面的可能失效
        definition.addPathDefinition("/**", "authc");
        // 记住我的过滤器,一定要放在最下面，不然回忆起了但是请求已经处理了
        definition.addPathDefinition("/**", "user");
        return definition;
    }

    /**
     * remember管理器
     */
    public CookieRememberMeManager rememberMe() {
        CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
        rememberMeManager.setCookie(rememberCookie());
        //设置加密密钥（16位），防止重启服务器后cookie失效
        rememberMeManager.setCipherKey("1234567890987654".getBytes());
        return rememberMeManager;
    }

    /**
     * rememberMe设置cookie
     */
    public SimpleCookie rememberCookie() {
        SimpleCookie cookie = new SimpleCookie("remember");
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60 * 60);
        return cookie;
    }

    /**
     * 重写重定向，去除JSESSIONID
     */
    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        // 去掉shiro登录时url里的JSESSIONID
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        return sessionManager;
    }

}
```

## 登录相关controller

> 因为登录相关的服务有许多相似性，放在一起也许可以精简代码

```
/**
 * 登录与注册模块
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    private EmployeeMapper employeeMapper;
    private ClientMapper clientMapper;

    @Autowired
    public void setEmployeeMapper(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    @Autowired
    public void setClientMapper(ClientMapper clientMapper) {
        this.clientMapper = clientMapper;
    }

    /**
     * 管理员登录
     *
     * @param loginVo 用户名与密码与记住我
     * @param session  登录成功的状态
     * @return 登录成功与失败
     */
    @PostMapping("/admin")
    public Result employeeLogin(@RequestBody LoginDto loginVo, HttpSession session) {
        // 获取subject
        Subject subject = SecurityUtils.getSubject();
        // 封装到token
        AuthenticationToken token = new MyToken(loginVo, RealmName.EmployeeRealm);
        // 进行验证
        return getResult(session, subject, token);
    }

    /**
     * 用户登录
     *
     * @param loginVo 用户名与密码与记住我
     * @param session  登录成功的状态
     * @return 登录成功与失败
     */
    @PostMapping("/user")
    public Result clientLogin(@RequestBody LoginDto loginVo, HttpSession session) {
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token = new MyToken(loginVo, RealmName.ClientRealm);
        return getResult(session, subject, token);
    }

    /**
     * 管理员与用户登录的验证
     *
     * @param session 登录状态
     * @param subject 存储登录成功后用户的信息
     * @param token   输入的账号、密码、记住我、验证角色
     * @return 验证结果
     */
    @NotNull
    private Result getResult(HttpSession session, Subject subject, AuthenticationToken token) {
        try {
            subject.login(token);// 进行验证
            session.setAttribute("user", token.getPrincipal().toString());
            return Result.success("登录成功");
        } catch (IncorrectCredentialsException e) {
            return Result.error("登录失败，密码错误");
        } catch (UnknownAccountException e) {
            return Result.error("登录失败，账号不存在");
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return Result.error("登录失败");
        }
    }

    /**
     * 登录成功的管理员信息
     *
     * @return id，账号，名字，电话，照片
     */
    @GetMapping("/admin")
    @RequiresRoles("nurse")
    public Employee getAdmin() {
        return (Employee) SecurityUtils.getSubject().getPrincipal();
    }

    /**
     * 登录成功的用户信息
     *
     * @return id，账号，名字，电话，照片
     */
    @GetMapping("/user")
    public Client getUser() {
        return (Client) SecurityUtils.getSubject().getPrincipal();
    }

    /**
     * 管理员修改自己的密码
     */
    @PutMapping("/admin")
    public Result updatePwd(@RequestBody Employee employee) {
        if (employee.getEmployeePassword().equals("")) {
            employee.setEmployeePassword(null);
        } else {
            Md5Hash md5Hash = new Md5Hash(employee.getEmployeePassword(), "pet");
            employee.setEmployeePassword(md5Hash.toHex());
        }
        if (employeeMapper.updateById(employee) > 0) {
            return Result.success("修改成功");
        } else {
            return Result.error("修改失败");
        }
    }

    /**
     * 用户修改自己的密码
     */
    @PutMapping("/user")
    public Result updatePwd02(@RequestBody Client client) {
        if (client.getClientPassword().equals("")) {
            client.setClientPassword(null);
        } else {
            Md5Hash md5Hash = new Md5Hash(client.getClientPassword(), "pet");
            client.setClientPassword(md5Hash.toHex());
        }
        if (clientMapper.updateById(client) > 0) {
            return Result.success("修改成功");
        } else {
            return Result.error("修改失败");
        }
    }
}
```

## 角色需求注解

> * 限定某个方法只能某个角色访问
> * (1月12日更新)但是让高权限角色同时也拥有低权限角色时，只需要`@RequiresRoles("nurse")`

这是or的验证方式，默认的是and`@RequiresRoles(value={"admin", "nephew"},logical= Logical.OR)`

完整示例

```

    @PutMapping
    @RequiresRoles(value={"admin", "nephew"},logical= Logical.OR)
    public Result update(@RequestBody Employee employee) {
        return Result.choice("修改", employeeService.updateById(employee));
    }

```

## 没有角色的异常切片

```
@ExceptionHandler(UnauthorizedException.class)//shiro:没有对应角色
public Result doUnauthorizedException() {
    return Result.error("没有操作权限");
}

@ExceptionHandler(UnauthenticatedException.class)//shiro:未登录
public Result doUnauthenticatedException() {
    return Result.error("未登录");
}

```