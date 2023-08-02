import reqApi from "@/request/reqAPI";
import {NoticeDto} from "@/model/DO/NoticeDto";

// 主页查询宠物
export const reqFourNotice = (): Promise<NoticeDto[]> =>
    reqApi<string, object>("notice/four")