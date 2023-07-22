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
/**
 * 个人中心：宠物详情的示例2
 */
export const exampleClientPet = (): Pet[] => {
    return [{
        "petId": 15,
        "petName": "波一斯",
        "petVariety": "波斯猫",
        "petSex": false,
        "petAge": "2016-12-15",
        "petStatus": "频繁掉毛",
        "petInfo": "等待有缘人领养",
        "clientId": 1,
        "petPhoto": "boss-cat.jpg",
        "createTime": "2022-12-23T11:03:06",
        "updateTime": "2023-02-18T14:28:14",
        "isDel": "0"
    }, {
        "petId": 3,
        "petName": "心海",
        "petVariety": "观赏鱼",
        "petSex": false,
        "petAge": "2000-01-13",
        "petStatus": "非常健康",
        "petInfo": "心海十分开心",
        "clientId": 1,
        "petPhoto": "f66eb858-e13c-438a-b235-8889d2d416be.png",
        "createTime": "2022-11-30T15:55:04",
        "updateTime": "2023-04-03T22:16:54",
        "isDel": "0"
    }]
}