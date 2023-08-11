import reqApi from "@/request/reqAPI";
import {Page} from "@/model/DO/Page";
import {DoctorDto} from "@/model/DO/DoctorDto";
import {PageQuery, YDoctorList} from "@/model/VO/BackQuery";
import {Doctor} from "@/model/entiy/Doctor";
import {Res} from "@/request/Res";
// 主页查询医生
export const reqFourDoctor = (): Promise<Page<DoctorDto>> =>
    reqApi<string, object>("doctor/page?numPage=1&pageSize=4")
// 用户查看许多医生
export const reqDoctorSee = (page: PageQuery): Promise<Page<DoctorDto>> =>
    reqApi<string, object>("doctor/page", {params: page})
// 用户查看一个医生详情
export const reqDoctorOne = (doctorId: number): Promise<DoctorDto> =>
    reqApi<string, object>("doctor/one?doctorId=" + doctorId)
// 后台医生列表
export const reqDoctorList = (page: YDoctorList): Promise<Page<DoctorDto>> =>
    reqApi<string, object>("doctor/page", {params: page})
// 管理员新增医生
export const reqAddDoctor = (data: Doctor): Promise<Res> =>
    reqApi.post<string, object>("doctor", data)
// 管理员修改医生
export const reqUpdateDoctor = (data: Doctor): Promise<Res> =>
    reqApi.put<string, object>("doctor", data)
// 管理员删除医生
export const reqDelDoctor = (id: number): Promise<Res> =>
    reqApi.delete<string, object>("doctor/" + id)
// 管理员批量删除医生
export const reqDelDoctorBatch = (ids: number[]): Promise<Res> =>
    reqApi.delete<string, object>("doctor/batch/" + ids)