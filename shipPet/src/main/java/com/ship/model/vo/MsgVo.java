package com.ship.model.vo;

import java.time.LocalDateTime;

/**
 * 消息类
 */
public record MsgVo(
        Integer msgId,
        String roleName,
        String rolePhoto,
        boolean isMine,
        String msgContent,
        boolean isImg,
        LocalDateTime createTime
) {
}
