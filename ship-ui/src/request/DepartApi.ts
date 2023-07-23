import {XDepartmentList} from "@/modal/VO/BackQuery";
import {Page} from "@/modal/DO/Page";
import reqApi from "@/request/reqAPI";
import {Department} from "@/modal/entiy/Department";

// 后台部门列表
export const reqDepartList = (page: XDepartmentList): Promise<Page<Department>> =>
    reqApi<string, object>("department/page", {params: page})