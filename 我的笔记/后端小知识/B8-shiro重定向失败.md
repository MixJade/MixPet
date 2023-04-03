# shiro重定向失败

* shiro有的时候重定向会失败，因为它会默认在重定向链接后面添加JSESSIONID
* 更过分的是，JSESSIONID前面是分号，直接导致路径访问失败
* 只能通过DefaultWebSessionManager的bean来取消掉这一行为

```
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
```

接着向securityManager里添加(当然这里为了方便观看，省略了其他配置)

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
        // 去掉JSESSIONID,防止重定向失败
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }
```