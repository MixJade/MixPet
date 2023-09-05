import {Notice} from "@/model/entiy/Notice";

export interface NoticeDto extends Notice {
    createName?: string | null;
    updateName?: string | null;
    textNotice: string;
}

/**
 * 空数据
 */
export const exampleNotice = (): NoticeDto => {
    return {
        noticeFile: "",
        noticeId: 0,
        noticeTitle: "",
        textNotice: ""
    }
}
