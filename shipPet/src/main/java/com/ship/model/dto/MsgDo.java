package com.ship.model.dto;

import java.time.LocalDateTime;

/**
 * 消息类
 */
public record MsgDo(
        Integer msgId,
        String roleName,
        String rolePhoto,
        boolean isMine,
        String msgContent,
        boolean isImg,
        LocalDateTime createTime
) {
}
