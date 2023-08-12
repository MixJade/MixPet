import reqApi from "@/request/reqAPI";
import {Employee} from "@/model/entiy/Employee";
import {XEmployeeList} from "@/model/VO/BackQuery";
import {Page} from "@/model/DO/Page";
import {Res} from "@/request/Res";

// 管理员的登陆信息
export const reqGetLoginE = (): Promise<Employee> =>
    reqApi<string, Employee>("/adminLog")
// 后台管理员列表
export const reqEmployeeList = (page: XEmployeeList): Promise<Page<Employee>> =>
    reqApi<string, Page<Employee>>("employee/page", {params: page})
// 管理员新增
export const reqAddEmployee = (data: Employee): Promise<Res> =>
    reqApi.post<string, Res>("employee", data)
// 管理员修改
export const reqUpdateEmployee = (data: Employee): Promise<Res> =>
    reqApi.put<string, Res>("employee", data)
// 管理员删除
export const reqDelEmployee = (id: number): Promise<Res> =>
    reqApi.delete<string, Res>("employee/" + id)
// 管理员批量删除
export const reqDelEmployeeBatch = (ids: number[]): Promise<Res> =>
    reqApi.delete<string, Res>("employee/batch/" + ids)