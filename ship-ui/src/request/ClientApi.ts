import {YClientList} from "@/modal/VO/BackQuery";
import {Page} from "@/modal/DO/Page";
import reqApi from "@/request/reqAPI";
import {Client} from "@/modal/entiy/Client";
import {Name} from "@/modal/entiy/Name";

// 后台用户列表
export const reqClientList = (page: YClientList): Promise<Page<Client>> =>
    reqApi<string, object>("client/page", {params: page})
// 后台下拉框查询用户姓名
export const reqClientName = (): Promise<Name[]> =>
    reqApi<string, object>("client")