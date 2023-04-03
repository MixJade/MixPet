package com.forge.ws;


import org.apache.shiro.SecurityUtils;

import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

/**
 * 在握手时，将shiro的登录信息存进websocket之中。
 * 因为ws是无法在请求头中携带token的
 */
public class ShiroEndpoint extends ServerEndpointConfig.Configurator {
    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        sec.getUserProperties().put("chatUser", SecurityUtils.getSubject().getPrincipal());
        super.modifyHandshake(sec, request, response);
    }
}

