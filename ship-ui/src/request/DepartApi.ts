import {XDepartmentList} from "@/model/VO/BackQuery";
import {Page} from "@/model/DO/Page";
import reqApi from "@/request/reqAPI";
import {Department} from "@/model/entiy/Department";

// 后台部门列表
export const reqDepartList = (page: XDepartmentList): Promise<Page<Department>> =>
    reqApi<string, object>("department/page", {params: page})