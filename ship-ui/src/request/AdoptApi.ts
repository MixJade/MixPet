import reqApi from "@/request/reqAPI";
import {Res} from "@/request/Res";
import {PetAskOne} from "@/model/VO/PetAskOne";
import {YAdoptList} from "@/model/VO/BackQuery";
import {Page} from "@/model/DO/Page";
import {AdoptDto} from "@/model/DO/AdoptDto";

// 判断是否领养
export const reqInAdopt = (petId: number): Promise<number> =>
    reqApi<string, number>("adopt/inAdopt?petId=" + petId)
// 申请领养
export const reqAdoptPet = (data: PetAskOne): Promise<Res> =>
    reqApi.post<string, Res>("adopt/adoptPet", data)
// 管理员后台领养列表
export const reqAdoptList = (page: YAdoptList): Promise<Page<AdoptDto>> =>
    reqApi<string, Page<AdoptDto>>("adopt/page", {params: page})