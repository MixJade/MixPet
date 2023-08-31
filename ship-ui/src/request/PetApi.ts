import {Page} from "@/model/DO/Page";
import reqApi from "@/request/reqAPI";
import {Pet} from "@/model/entiy/Pet";
import {PageQuery, YPetList} from "@/model/VO/BackQuery";
import {PetDto} from "@/model/DO/PetDto";
import {Res} from "@/request/Res";
import {NameVo} from "@/model/VO/NameVo";

// 主页查询宠物
export const reqFourPet = (): Promise<Page<Pet>> =>
    reqApi<string, Page<Pet>>("pet/four?numPage=1&pageSize=4")
// 查看领养宠物
export const reqPetSee = (page: PageQuery): Promise<Page<Pet>> =>
    reqApi<string, Page<Pet>>("pet/four", {params: page})
// 查看宠物详情
export const reqPetOne = (petId: number): Promise<Pet> =>
    reqApi<string, Pet>("pet/one?petId=" + petId)
// 查看用户的宠物
export const reqClientPet = (): Promise<Pet[]> =>
    reqApi<string, Pet[]>("pet/clientOne")
// 管理员后台宠物列表
export const reqPetList = (page: YPetList): Promise<Page<PetDto>> =>
    reqApi<string, Page<PetDto>>("pet/page", {params: page})
// 管理员新增宠物
export const reqAddPet = (data: Pet): Promise<Res> =>
    reqApi.post<string, Res>("pet", data)
// 管理员修改宠物
export const reqUpdatePet = (data: Pet): Promise<Res> =>
    reqApi.put<string, Res>("pet", data)
// 管理员删除宠物
export const reqDelPet = (id: number): Promise<Res> =>
    reqApi.delete<string, Res>("pet/" + id)
// 管理员批量删除宠物
export const reqDelPetBatch = (ids: number[]): Promise<Res> =>
    reqApi.delete<string, Res>("pet/batch/" + ids)
// 管理员通过用户名查看宠物
export const reqPetNameByClientId = (clientId: number, petId: number): Promise<NameVo[]> => {
    if (petId == null || petId === 0) {
        return reqApi<string, NameVo[]>("pet/client?clientId=" + clientId)
    } else return reqApi<string, NameVo[]>("pet/client?clientId=" + clientId + "&petId=" + petId)
}
