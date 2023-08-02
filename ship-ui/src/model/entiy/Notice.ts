export interface Notice {
    noticeId: number;
    noticeTitle: string;
    noticeFile: string;
    creatId?: null | number;
    createTime?: null | string;
    updateId?: null | number;
    updateTime?: null | string;
    isDisable?: null | boolean;
}