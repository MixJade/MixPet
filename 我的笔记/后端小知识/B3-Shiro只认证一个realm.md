# Shiro只认证一个realm

> * 和认证多个realm有很大的变动
> * 另外，多个realm可以配置认证策略，默认的是至少有一个通过就认证成功
> * 但是多个realm的报错就不细致了，而且会出现“串台”的情况，即：管理员的账号在用户入口登录成功

```
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
    // 加密对象存储在realm中,realm存在DefaultWebSecurityManager中
    employeeRealm.setCredentialsMatcher(matcher);
    securityManager.setRealm(employeeRealm);
    return securityManager;
    }
```