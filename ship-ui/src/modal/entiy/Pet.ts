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
}

/**
 * 空数据
 */
export const examplePet = (): Pet => {
    return {
        "petId": 0,
        "petName": "",
        "petVariety": "",
        "petSex": false,
        "petAge": "",
        "petStatus": "",
        "petInfo": "",
        "clientId": null,
        "petPhoto": "",
    }
}