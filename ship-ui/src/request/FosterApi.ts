import {Page} from "@/modal/DO/Page";
import reqApi from "@/request/reqAPI";
import {FosterCardDto} from "@/modal/DO/FosterCardDto";

// 主页查询宠物
export const reqFosterPet = (pageSize: number): Promise<Page<FosterCardDto>> =>
    reqApi<string, object>("foster/pet?pageSize=" + pageSize)