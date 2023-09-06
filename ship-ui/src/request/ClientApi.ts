import {YClientList} from "@/model/VO/BackQuery";
import {Page} from "@/model/DO/Page";
import reqApi from "@/request/reqAPI";
import {Client} from "@/model/entiy/Client";
import {NameVo} from "@/model/VO/NameVo";
import {Res} from "@/request/Res";

// 后台用户列表
export const reqClientList = (page: YClientList): Promise<Page<Client>> =>
    reqApi<string, Page<Client>>("client/page", {params: page})
// 后台下拉框查询用户姓名
export const reqClientName = (): Promise<NameVo[]> =>
    reqApi<string, NameVo[]>("client")
// 管理员新增用户
export const reqAddClient = (data: Client): Promise<Res> =>
    reqApi.post<string, Res>("client", data)
// 管理员修改用户
export const reqUpdateClient = (data: Client): Promise<Res> =>
    reqApi.put<string, Res>("client", data)
// 管理员删除用户
export const reqDelClient = (id: number): Promise<Res> =>
    reqApi.delete<string, Res>("client/" + id)
// 管理员批量删除用户
export const reqDelClientBatch = (ids: number[]): Promise<Res> =>
    reqApi.delete<string, Res>("client/batch/" + ids)
// 订单下拉框查询有宠物的用户姓名，带上已有用户
export const reqClientNameHavePet = (clientId: number | null): Promise<NameVo[]> => {
    if (clientId == null || clientId == 0) return reqApi<string, NameVo[]>("client/full")
    else return reqApi<string, NameVo[]>("client/full?clientId=" + clientId)
}
// 用户修改自己的资料
export const reqUpdateClientSelf = (data: Client): Promise<Res> =>
    reqApi.put<string, Res>("client/self", data)
