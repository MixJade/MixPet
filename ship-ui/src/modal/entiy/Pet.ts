/**
 * 宠物详情
 */
export interface Pet {
    petId: number;
    petName: string;
    petVariety: string;
    petSex: boolean;
    petAge: string;
    petStatus: string | null;
    petInfo: string;
    clientId: number | null;
    petPhoto: string;
    createTime: string | null;
    updateTime: string | null;
    isDel: string | null;
}
