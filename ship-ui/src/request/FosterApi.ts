import {Page} from "@/modal/DO/Page";
import reqApi from "@/request/reqAPI";
import {FosterCardDto} from "@/modal/DO/FosterCardDto";
import {YFosterList} from "@/modal/VO/BackQuery";
import {FosterDto} from "@/modal/DO/FosterDto";

// 主页查询宠物
export const reqFosterPet = (pageSize: number): Promise<Page<FosterCardDto>> =>
    reqApi<string, object>("foster/pet?pageSize=" + pageSize)
// 后台寄养列表
export const reqFosterList = (page: YFosterList): Promise<Page<FosterDto>> =>
    reqApi<string, object>("foster/page", {params: page})