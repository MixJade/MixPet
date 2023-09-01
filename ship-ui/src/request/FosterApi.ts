import {Page} from "@/model/DO/Page";
import reqApi from "@/request/reqAPI";
import {FosterCardDto} from "@/model/DO/FosterCardDto";
import {YFosterList} from "@/model/VO/BackQuery";
import {FosterDto} from "@/model/DO/FosterDto";
import {Foster} from "@/model/entiy/Foster";
import {Res} from "@/request/Res";

// 主页查询宠物
export const reqFosterPet = (pageSize: number): Promise<Page<FosterCardDto>> =>
    reqApi<string, Page<FosterCardDto>>("foster/pet?pageSize=" + pageSize)
// 后台寄养列表
export const reqFosterList = (page: YFosterList): Promise<Page<FosterDto>> =>
    reqApi<string, Page<FosterDto>>("foster/page", {params: page})
// 管理员新增领养
export const reqAddFoster = (data: Foster): Promise<Res> =>
    reqApi.post<string, Res>("foster", data)
// 管理员修改领养
export const reqUpdateFoster = (data: Foster): Promise<Res> =>
    reqApi.put<string, Res>("foster", data)
// 管理员删除领养
export const reqDelFoster = (id: number): Promise<Res> =>
    reqApi.delete<string, Res>("foster/" + id)
// 管理员批量删除领养
export const reqDelFosterBatch = (ids: number[]): Promise<Res> =>
    reqApi.delete<string, Res>("foster/batch/" + ids)
