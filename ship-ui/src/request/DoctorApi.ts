import reqApi from "@/request/reqAPI";
import {Page} from "@/modal/DO/Page";
import {DoctorDto} from "@/modal/DO/DoctorDto";
import {PageQuery} from "@/modal/VO/BackQuery";
// 主页查询医生
export const reqFourDoctor = (): Promise<Page<DoctorDto>> =>
    reqApi<string, object>("doctor/page?numPage=1&pageSize=4")
// 用户查看许多医生
export const reqDoctorSee = (page: PageQuery): Promise<Page<DoctorDto>> =>
    reqApi<string, object>("doctor/page", {params: page})
// 用户查看一个医生详情
export const reqDoctorOne = (doctorId: number): Promise<DoctorDto> =>
    reqApi<string, object>("doctor/one?doctorId=" + doctorId)