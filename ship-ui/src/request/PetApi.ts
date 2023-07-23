import {Page} from "@/modal/DO/Page";
import reqApi from "@/request/reqAPI";
import {Pet} from "@/modal/entiy/Pet";
import {PageQuery, YPetList} from "@/modal/VO/BackQuery";
import {PetDto} from "@/modal/DO/PetDto";

// 主页查询宠物
export const reqFourPet = (): Promise<Page<Pet>> =>
    reqApi<string, object>("pet/four?numPage=1&pageSize=4")
// 查看领养宠物
export const reqPetSee = (page: PageQuery): Promise<Page<Pet>> =>
    reqApi<string, object>("pet/four", {params: page})
// 查看宠物详情
export const reqPetOne = (petId: number): Promise<Pet> =>
    reqApi<string, object>("pet/one?petId=" + petId)
// 查看用户的宠物
export const reqClientPet = (): Promise<Pet[]> =>
    reqApi<string, object>("pet/clientOne")
// 管理员后台宠物列表
export const reqPetList = (page: YPetList): Promise<Page<PetDto>> =>
    reqApi<string, object>("pet/page", {params: page})