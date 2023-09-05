import reqApi from "@/request/reqAPI";
import {Page} from "@/model/DO/Page";
import {DoctorDto} from "@/model/DO/DoctorDto";
import {PageQuery, YDoctorList} from "@/model/VO/BackQuery";
import {Doctor} from "@/model/entiy/Doctor";
import {Res} from "@/request/Res";
import {NameVo} from "@/model/VO/NameVo";
import {AppointDto2} from "@/model/DO/AppointDto2";
import {DealAppointVo} from "@/model/VO/DealAppointVo";
import {Appoint} from "@/model/entiy/Appoint";
// 主页查询医生
export const reqFourDoctor = (): Promise<Page<DoctorDto>> =>
    reqApi<string, Page<DoctorDto>>("doctor/page?numPage=1&pageSize=4")
// 用户查看许多医生
export const reqDoctorSee = (page: PageQuery): Promise<Page<DoctorDto>> =>
    reqApi<string, Page<DoctorDto>>("doctor/page", {params: page})
// 用户查看一个医生详情
export const reqDoctorOne = (doctorId: number): Promise<DoctorDto> =>
    reqApi<string, DoctorDto>("doctor/one?doctorId=" + doctorId)
// 后台医生列表
export const reqDoctorList = (page: YDoctorList): Promise<Page<DoctorDto>> =>
    reqApi<string, Page<DoctorDto>>("doctor/page", {params: page})
// 管理员新增医生
export const reqAddDoctor = (data: Doctor): Promise<Res> =>
    reqApi.post<string, Res>("doctor", data)
// 管理员修改医生
export const reqUpdateDoctor = (data: Doctor): Promise<Res> =>
    reqApi.put<string, Res>("doctor", data)
// 管理员删除医生
export const reqDelDoctor = (id: number): Promise<Res> =>
    reqApi.delete<string, Res>("doctor/" + id)
// 管理员批量删除医生
export const reqDelDoctorBatch = (ids: number[]): Promise<Res> =>
    reqApi.delete<string, Res>("doctor/batch/" + ids)
// 医生的登陆信息
export const reqGetLoginD = (): Promise<DoctorDto> =>
    reqApi<string, DoctorDto>("/doctorLog")
// 医生修改个人简历
export const reqUpdateLoginD = (data: Doctor): Promise<Res> =>
    reqApi.put<string, Res>("/doctorLog", data)
// 医生的姓名列表
export const reqDoctorName = (): Promise<NameVo[]> =>
    reqApi<string, NameVo[]>("doctor")
// 后台通过科室查询医生的姓名列表
export const reqDoctorNameByDepartId = (departmentId: number): Promise<NameVo[]> =>
    reqApi<string, NameVo[]>("doctor/department?departmentId=" + departmentId)
// 医生的查看自己的挂号
export const reqDoctorAppoint = (): Promise<AppointDto2[]> =>
    reqApi<string, AppointDto2[]>("doctorLog/appoint")
// 医生的处理自己的挂号
export const reqDoctorAddAppoint = (appoint: Appoint): Promise<Res> =>
    reqApi.post<string, Res>("doctorLog/appoint", appoint)
// 医生的新增自己的挂号
export const reqDealAppoint = (dealAppointVo: DealAppointVo): Promise<Res> =>
    reqApi.put<string, Res>("doctorLog/appoint/deal", dealAppointVo)
