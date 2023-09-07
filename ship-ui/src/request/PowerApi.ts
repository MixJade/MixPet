import reqApi from "@/request/reqAPI";
import {Res} from "@/request/Res";
import {LoginVo, RoleEnum} from "@/model/VO/LoginVo";
import {Client} from "@/model/entiy/Client";
import {RegisterVo} from "@/model/VO/RegisterVo";
//登录接口
export const reqLogin = (data: LoginVo): Promise<Res> =>
    reqApi.post<string, Res>("cao/toLogin", data)
// 退出登陆
export const reqLogout = (): Promise<Res> =>
    reqApi<string, Res>("cao/logout")
// 判断当前登陆角色
export const reqGetLogin = (): Promise<RoleEnum> =>
    reqApi<string, RoleEnum>("login")
// 当前登陆的用户信息
export const reqLoginUser = (): Promise<Client> =>
    reqApi<string, Client>("login/user")
// 注册时发送邮件
export const reqSendRegMail = (mail: string): Promise<Res> =>
    reqApi<string, Res>("login/regMail?mail=" + mail)
// 注册时检查当前账号是否存在
export const reqRegisExist = (regUse: string): Promise<Res> =>
    reqApi<string, Res>("login/isExist?regUse=" + regUse)
// 注册
export const reqRegister = (data: RegisterVo): Promise<Res> =>
    reqApi.post<string, Res>("login/register", data)
// 找回密码发送邮件
export const reqSendFindMail = (mail: string, username: string): Promise<Res> =>
    reqApi<string, Res>("login/findMail?mail=" + mail + "&username=" + username)
// 找回密码
export const reqFindPwd = (data: RegisterVo): Promise<Res> =>
    reqApi.post<string, Res>("login/find", data)
