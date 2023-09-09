/**
 * 聊天消息，专门用来发送
 */
export interface Msg {
    clientId?: number;
    doctorId?: number;
    msgContent: string;
    isImg: boolean;
    createTime?: string;
}
