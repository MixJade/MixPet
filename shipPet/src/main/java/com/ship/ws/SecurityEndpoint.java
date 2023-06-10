package com.ship.ws;


import com.ship.util.UserUtil;
import jakarta.websocket.HandshakeResponse;
import jakarta.websocket.server.HandshakeRequest;
import jakarta.websocket.server.ServerEndpointConfig;

/**
 * 在握手时，将shiro的登录信息存进websocket之中。
 * 因为ws是无法在请求头中携带token的
 */
public class SecurityEndpoint extends ServerEndpointConfig.Configurator {
    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        sec.getUserProperties().put("chatUser", UserUtil.getUser());
        super.modifyHandshake(sec, request, response);
    }
}

