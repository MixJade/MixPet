import reqApi from "@/request/reqAPI";
import {NoticeDto} from "@/model/DO/NoticeDto";
import {YNoticeList} from "@/model/VO/BackQuery";
import {Page} from "@/model/DO/Page";
import {Notice} from "@/model/entiy/Notice";
import {Res} from "@/request/Res";

// 主页查询宠物
export const reqFourNotice = (): Promise<NoticeDto[]> =>
    reqApi<string, NoticeDto[]>("notice/four")
// 后台公告列表
export const reqNoticeList = (page: YNoticeList): Promise<Page<NoticeDto>> =>
    reqApi<string, Page<NoticeDto>>("notice/page", {params: page})
// 管理员新增公告
export const reqAddNotice = (data: Notice): Promise<Res> =>
    reqApi.post<string, Res>("notice", data)
// 管理员修改公告
export const reqUpdateNotice = (data: Notice): Promise<Res> =>
    reqApi.put<string, Res>("notice", data)
// 管理员删除公告
export const reqDelNotice = (id: number): Promise<Res> =>
    reqApi.delete<string, Res>("notice/" + id)
// 管理员批量删除公告
export const reqDelNoticeBatch = (ids: number[]): Promise<Res> =>
    reqApi.delete<string, Res>("notice/batch/" + ids)
// 禁用与启用
export const reqDisableSet = (id: number, isDis: boolean): Promise<Res> =>
    reqApi.delete<string, Res>(`notice/disable/${id}?isDis=${isDis}`)
// 清理冗余资源
export const reqDelImg = (): Promise<Res> =>
    reqApi.delete<string, Res>(`notice/delImg`)
