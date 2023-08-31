import reqApi from "@/request/reqAPI";
import {AppointDto} from "@/model/DO/AppointDto";
import {YAppointList} from "@/model/VO/BackQuery";
import {Page} from "@/model/DO/Page";
import {Res} from "@/request/Res";
import {Appoint} from "@/model/entiy/Appoint";
// 用户查看一个医生的挂号
export const reqDoctorOneA = (doctorId: number): Promise<AppointDto[]> =>
    reqApi<string, AppointDto[]>("appointment/doctor?doctorId=" + doctorId)
// 用户个人中心的挂号
export const reqClientAppoint = (): Promise<AppointDto[]> =>
    reqApi<string, AppointDto[]>("appointment/client")
// 后台挂号列表
export const reqAppointList = (page: YAppointList): Promise<Page<AppointDto>> =>
    reqApi<string, Page<AppointDto>>("appointment/page", {params: page})
// 管理员新增挂号
export const reqAddAppoint = (data: Appoint): Promise<Res> =>
    reqApi.post<string, Res>("appointment", data)
// 管理员修改挂号
export const reqUpdateAppoint = (data: Appoint): Promise<Res> =>
    reqApi.put<string, Res>("appointment", data)
// 管理员删除挂号
export const reqDelAppoint = (id: number): Promise<Res> =>
    reqApi.delete<string, Res>("appointment/" + id)
// 管理员批量删除挂号
export const reqDelAppointBatch = (ids: number[]): Promise<Res> =>
    reqApi.delete<string, Res>("appointment/batch/" + ids)
