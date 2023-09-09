import reqApi from "@/request/reqAPI";
import {ChatGroup} from "@/model/DO/ChatGroup";
import {MsgDo} from "@/model/DO/MsgDo";
import {NameDo} from "@/model/DO/NameDo";
import {Msg} from "@/model/entiy/Msg";
import {Res} from "@/request/Res";
// 用户查看最近聊天的医生ID
export const reqClientGetNearDoctor = (): Promise<NameDo> =>
    reqApi<string, NameDo>("msg/client/nearDoctor")
// 用户：查询特定用户与医生对话
export const reqClientMsgList = (doctorId: number): Promise<MsgDo[]> =>
    reqApi<string, MsgDo[]>("msg/client/aDoctor?doctorId=" + doctorId)
// 用户：查询用户谈过话的医生
export const reqClientMsgGroup = (doctorId: number): Promise<ChatGroup[]> =>
    reqApi<string, ChatGroup[]>("msg/client/doctorGroup?doctorId=" + doctorId)
// 用户：发送消息
export const reqSendClientMsg = (msg: Msg): Promise<Res> =>
    reqApi.post<string, Res>("msg/client", msg)
// 医生查看最近聊天的用户ID
export const reqDoctorGetNearClient = (): Promise<NameDo> =>
    reqApi<string, NameDo>("msg/doctor/nearClient")
// 医生：查询特定医生与用户对话
export const reqDoctorMsgList = (clientId: number): Promise<MsgDo[]> =>
    reqApi<string, MsgDo[]>("msg/doctor/aClient?clientId=" + clientId)
// 医生：医生谈过话的用户
export const reqDoctorMsgGroup = (clientId: number): Promise<ChatGroup[]> =>
    reqApi<string, ChatGroup[]>("msg/doctor/clientGroup?clientId=" + clientId)
// 医生：发送消息
export const reqSendDoctorMsg = (msg: Msg): Promise<Res> =>
    reqApi.post<string, Res>("msg/doctor", msg)
