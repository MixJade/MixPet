import reqApi from "@/request/reqAPI";
import {Employee} from "@/modal/entiy/Employee";
import {XEmployeeList} from "@/modal/VO/BackQuery";
import {Page} from "@/modal/DO/Page";

// 管理员的登陆信息
export const reqGetLoginE = (): Promise<Employee> =>
    reqApi<string, object>("/adminLog")
// 后台管理员列表
export const reqEmployeeList = (page: XEmployeeList): Promise<Page<Employee>> =>
    reqApi<string, object>("employee/page", {params: page})