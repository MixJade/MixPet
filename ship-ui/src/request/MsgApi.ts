import reqApi from "@/request/reqAPI";
import {ChatGroup} from "@/model/DO/ChatGroup";
import {MsgDo} from "@/model/DO/MsgDo";
// 用户查看最近聊天的医生ID
export const reqDoctorIdMsg = (): Promise<number> =>
    reqApi<string, number>("msg")
export const reqClientMsgList = (doctorId: number): Promise<MsgDo[]> =>
    reqApi<string, MsgDo[]>("msg/aDoctor?doctorId=" + doctorId)
export const reqClientMsgGroup = (doctorId: number): Promise<ChatGroup[]> =>
    reqApi<string, ChatGroup[]>("msg/group?doctorId=" + doctorId)
// 医生查看最近聊天的用户ID
export const reqClientIDMsg = (): Promise<number> =>
    reqApi<string, number>("msg/d")
export const reqDoctorMsgList = (clientId: number): Promise<MsgDo[]> =>
    reqApi<string, MsgDo[]>("msg/aClient?clientId=" + clientId)
export const reqDoctorMsgGroup = (clientId: number): Promise<ChatGroup[]> =>
    reqApi<string, ChatGroup[]>("msg/d/group?clientId=" + clientId)
