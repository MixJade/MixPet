import {YClientList} from "@/model/VO/BackQuery";
import {Page} from "@/model/DO/Page";
import reqApi from "@/request/reqAPI";
import {Client} from "@/model/entiy/Client";
import {Name} from "@/model/entiy/Name";
import {Res} from "@/request/Res";

// 后台用户列表
export const reqClientList = (page: YClientList): Promise<Page<Client>> =>
    reqApi<string, object>("client/page", {params: page})
// 后台下拉框查询用户姓名
export const reqClientName = (): Promise<Name[]> =>
    reqApi<string, object>("client")
// 管理员新增用户
export const reqAddClient = (data: Client): Promise<Res> =>
    reqApi.post<string, object>("client", data)
// 管理员修改用户
export const reqUpdateClient = (data: Client): Promise<Res> =>
    reqApi.put<string, object>("client", data)
// 管理员删除用户
export const reqDelClient = (id: number): Promise<Res> =>
    reqApi.delete<string, object>("client/" + id)
// 管理员批量删除用户
export const reqDelClientBatch = (ids: number[]): Promise<Res> =>
    reqApi.delete<string, object>("client/batch/" + ids)