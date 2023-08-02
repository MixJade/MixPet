import reqApi from "@/request/reqAPI";
import {Res} from "@/request/Res";
import {LoginVo, RoleEnum} from "@/model/VO/LoginVo";
import {Client} from "@/model/entiy/Client";
//登录接口
export const reqLogin = (data: LoginVo): Promise<Res> =>
    reqApi.post<string, object>("cao/toLogin", data)
// 退出登陆
export const reqLogout = (): Promise<Res> =>
    reqApi<string, object>("cao/logout")
// 判断当前登陆角色
export const reqGetLogin = (): Promise<RoleEnum> =>
    reqApi<string, object>("login")
// 当前登陆的用户信息
export const reqLoginUser = (): Promise<Client> =>
    reqApi<string, object>("login/user")