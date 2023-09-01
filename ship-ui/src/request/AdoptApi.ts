import reqApi from "@/request/reqAPI";
import {Res} from "@/request/Res";
import {PetAskOne} from "@/model/VO/PetAskOne";
import {YAdoptList} from "@/model/VO/BackQuery";
import {Page} from "@/model/DO/Page";
import {AdoptDto} from "@/model/DO/AdoptDto";
import {Adopt} from "@/model/entiy/Adopt";
import {AdoptVo} from "@/model/VO/AdoptVo";

// 判断是否领养
export const reqInAdopt = (petId: number): Promise<number> =>
    reqApi<string, number>("adopt/inAdopt?petId=" + petId)
// 申请领养
export const reqAdoptPet = (data: PetAskOne): Promise<Res> =>
    reqApi.post<string, Res>("adopt/adoptPet", data)
// 管理员后台领养列表
export const reqAdoptList = (page: YAdoptList): Promise<Page<AdoptDto>> =>
    reqApi<string, Page<AdoptDto>>("adopt/page", {params: page})
// 管理员新增领养
export const reqAddAdopt = (data: Adopt): Promise<Res> =>
    reqApi.post<string, Res>("adopt", data)
// 管理员修改领养
export const reqUpdateAdopt = (data: Adopt): Promise<Res> =>
    reqApi.put<string, Res>("adopt", data)
// 管理员删除领养
export const reqDelAdopt = (id: number): Promise<Res> =>
    reqApi.delete<string, Res>("adopt/" + id)
// 管理员批量删除领养
export const reqDelAdoptBatch = (ids: number[]): Promise<Res> =>
    reqApi.delete<string, Res>("adopt/batch/" + ids)
// 管理员审核领养订单
export const reqAdoptAdopt = (adoptVo: AdoptVo): Promise<Res> =>
    reqApi.put<string, Res>("adopt/adopt", adoptVo)
