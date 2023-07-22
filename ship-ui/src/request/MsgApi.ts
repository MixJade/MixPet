import reqApi from "@/request/reqAPI";
import {ChatGroup} from "@/modal/VO/ChatGroup";
import {MsgVo} from "@/modal/VO/MsgVo";
// 用户查看最近聊天的医生
export const reqClientMsg = (): Promise<number> =>
    reqApi<string, number>("msg")
export const reqClientMsgList = (doctorId: number): Promise<MsgVo[]> =>
    reqApi<string, object>("msg/aDoctor?doctorId=" + doctorId)
export const reqClientMsgGroup = (doctorId: number): Promise<ChatGroup[]> =>
    reqApi<string, object>("msg/group?doctorId=" + doctorId)