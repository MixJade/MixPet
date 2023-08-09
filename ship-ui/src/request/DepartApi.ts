import {XDepartmentList} from "@/model/VO/BackQuery";
import {Page} from "@/model/DO/Page";
import reqApi from "@/request/reqAPI";
import {DepartDto} from "@/model/DO/DepartDto";
import {Res} from "@/request/Res";
import {Department} from "@/model/entiy/Department";

// 后台科室列表
export const reqDepartList = (page: XDepartmentList): Promise<Page<DepartDto>> =>
    reqApi<string, object>("department/page", {params: page})
// 科室新增
export const reqAddDepart = (data: Department): Promise<Res> =>
    reqApi.post<string, object>("department", data)
// 科室修改
export const reqUpdateDepart = (data: Department): Promise<Res> =>
    reqApi.put<string, object>("department", data)
// 科室删除
export const reqDelDepart = (id: number): Promise<Res> =>
    reqApi.delete<string, object>("department/" + id)
// 科室批量删除
export const reqDelDepartBatch = (ids: number[]): Promise<Res> =>
    reqApi.delete<string, object>("department/batch/" + ids)