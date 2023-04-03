package com.forge.shiro.realm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.forge.entity.Client;
import com.forge.mapper.ClientMapper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Role;
import org.springframework.stereotype.Component;

/**
 * 用户登录验证
 */
@Component
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
public class ClientRealm extends AuthorizingRealm {
    private static final Logger log = LoggerFactory.getLogger(ClientRealm.class);
    private final ClientMapper clientMapper;

    @Lazy
    @Autowired
    public ClientRealm(ClientMapper clientMapper) {
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
        if (!(principalCollection.getPrimaryPrincipal() instanceof Client)) return null;
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRole("client");
        log.info("当前角色为用户");
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
            log.warn("啥也不是");
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
