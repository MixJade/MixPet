package com.forge.shiro.authentic;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;

import java.util.Collection;

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
        RealmEnum realmNow = myToken.getLoginType();// 登录类型
        Collection<Realm> realms = getRealms();// 所有Realm
        // 使用类名包含枚举,区分realm
        for (Realm realm : realms) {
            if (realm.getName().contains(realmNow.getName())) return doSingleRealmAuthentication(realm, myToken);
        }
        return super.doAuthenticate(aToken);
    }
}