import {Notice} from "@/modal/entiy/Notice";

export interface NoticeDto extends Notice {
    createName?: string | null;
    updateName?: string | null;
    textNotice: string;
}