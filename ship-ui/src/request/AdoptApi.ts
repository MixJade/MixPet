import reqApi from "@/request/reqAPI";
import {Res} from "@/request/Res";
import {PetAskOne} from "@/modal/VO/PetAskOne";

// 判断是否领养
export const reqInAdopt = (petId: number): Promise<number> =>
    reqApi<string, number>("adopt/inAdopt?petId=" + petId)
// 申请领养
export const reqAdoptPet = (data: PetAskOne): Promise<Res> =>
    reqApi.post<string, object>("adopt/adoptPet", data)