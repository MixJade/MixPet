package com.ship.ws;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ship.entity.Client;
import com.ship.entity.Doctor;
import com.ship.entity.Msg;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 通信类
 */
@ServerEndpoint(value = "/chat", configurator = SecurityEndpoint.class)
@Component
public class ChatEndpoint {
    private static final Logger log = LoggerFactory.getLogger(ChatEndpoint.class);
    /**
     * 用来存储每个用户客户端对象的ChatEndpoint对象
     */
    private static final Map<Integer, ChatEndpoint> onlineClients = new ConcurrentHashMap<>();
    private static final Map<Integer, ChatEndpoint> onlineDoctors = new ConcurrentHashMap<>();

    /**
     * 通过session对象,发消息给指定的用户
     */
    private Session session;
    /**
     * 握手时所存储的shiro登录信息
     */
    private Object object;

    /**
     * 连接建立
     *
     * @param session 当前用户的session
     */
    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        this.session = session;
        this.object = config.getUserProperties().get("chatUser");
        if (object instanceof Client client) {
            log.info("用户【{}】加入聊天", client.getClientName());
            onlineClients.put(client.getClientId(), this);
        } else if (object instanceof Doctor doctor) {
            log.info("医生【{}】加入聊天", doctor.getDoctorName());
            onlineDoctors.put(doctor.getDoctorId(), this);
        } else log.info("当前用户无法识别:{}", object);
    }

    /**
     * 收到客户端发送数据，并转发给接受者
     *
     * @param message 字符串化的json数据
     */
    @OnMessage
    public void onMessage(String message) {
        //将数据转换成对象
        try {
            ObjectMapper mapper = new ObjectMapper();
            Msg msg = mapper.readValue(message, Msg.class);
            if (object instanceof Client client) {
                // 信息为客户发给医生
                Integer doctorId = msg.getDoctorId();
                if (!onlineDoctors.containsKey(doctorId)) return;
                msg.setClientId(client.getClientId());
                String msgJson = msgToString(msg);
                onlineDoctors.get(doctorId).session.getBasicRemote().sendText(msgJson);
            } else if (object instanceof Doctor doctor) {
                // 信息为医生发给客户
                Integer clientId = msg.getClientId();
                if (!onlineClients.containsKey(clientId)) return;
                msg.setDoctorId(doctor.getDoctorId());
                String msgJson = msgToString(msg);
                onlineClients.get(clientId).session.getBasicRemote().sendText(msgJson);
            }
        } catch (JsonProcessingException e) {
            log.warn("信息解析失败");
        } catch (IOException e) {
            log.warn("信息发送失败");
        }
    }

    /**
     * 链接关闭
     */
    @OnClose
    public void onClose() {
        //从容器中删除指定的用户
        if (object instanceof Client client) {
            log.info("用户【{}】退出", client.getClientName());
            onlineClients.remove(client.getClientId());
        } else if (object instanceof Doctor doctor) {
            log.info("医生【{}】退出", doctor.getDoctorName());
            onlineDoctors.remove(doctor.getDoctorId());
        }
    }

    /**
     * 将对象转成json格式的字符串
     */
    private String msgToString(Msg msg) {
        try {
            return (new ObjectMapper()).writeValueAsString(msg);
        } catch (JsonProcessingException e) {
            log.warn("信息String化失败");
        }
        return null;
    }
}

