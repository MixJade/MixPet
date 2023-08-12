import reqApi from "@/request/reqAPI";
import {ChatGroup} from "@/model/VO/ChatGroup";
import {MsgVo} from "@/model/VO/MsgVo";
// 用户查看最近聊天的医生
export const reqClientMsg = (): Promise<number> =>
    reqApi<string, number>("msg")
export const reqClientMsgList = (doctorId: number): Promise<MsgVo[]> =>
    reqApi<string, MsgVo[]>("msg/aDoctor?doctorId=" + doctorId)
export const reqClientMsgGroup = (doctorId: number): Promise<ChatGroup[]> =>
    reqApi<string, ChatGroup[]>("msg/group?doctorId=" + doctorId)