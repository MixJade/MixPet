export interface Notice {
    noticeId: number;
    noticeTitle: string;
    noticeFile: string;
    createTime?: null | string;
    updateTime?: null | string;
    isDisable?: null | boolean;
}
