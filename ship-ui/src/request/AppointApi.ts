import reqApi from "@/request/reqAPI";
import {AppointDto} from "@/model/DO/AppointDto";
import {YAppointList} from "@/model/VO/BackQuery";
import {Page} from "@/model/DO/Page";
// 用户查看一个医生的挂号
export const reqDoctorOneA = (doctorId: number): Promise<AppointDto[]> =>
    reqApi<string, object>("appointment/doctor?doctorId=" + doctorId)
// 用户个人中心的挂号
export const reqClientAppoint = (): Promise<AppointDto[]> =>
    reqApi<string, object>("appointment/client")
// 后台挂号列表
export const reqAppointList = (page: YAppointList): Promise<Page<AppointDto>> =>
    reqApi<string, object>("appointment/page", {params: page})