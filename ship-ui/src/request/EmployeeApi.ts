import reqApi from "@/request/reqAPI";
import {Employee} from "@/model/entiy/Employee";
import {XEmployeeList} from "@/model/VO/BackQuery";
import {Page} from "@/model/DO/Page";

// 管理员的登陆信息
export const reqGetLoginE = (): Promise<Employee> =>
    reqApi<string, object>("/adminLog")
// 后台管理员列表
export const reqEmployeeList = (page: XEmployeeList): Promise<Page<Employee>> =>
    reqApi<string, object>("employee/page", {params: page})